package com.motang.motangge.task;

import com.motang.motangge.common.constant.BookConstants;
import com.motang.motangge.common.exception.BookException;
import com.motang.motangge.entity.*;
import com.motang.motangge.mapper.BookMapper;
import com.motang.motangge.common.utils.HttpUtils;
import com.motang.motangge.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
//
//@EnableScheduling
//@Component
@Slf4j
public class BookTask {

    @Autowired
    private HttpUtils hus;

    @Autowired
    private IBookService bookService;

    @Autowired
    private IBookCategoryService categoryService;

    @Autowired
    private IBookChapterService  chapterService;

    @Autowired
    private IAuthorService authorService;

    @Autowired
    private IBookContentService bookContentService;
    /**小说网站地址*/
    private static final String BOOK_URL ="https://xiaoshuo.sogou.com";
    /**小说种类地址*/
    private static final String CATEGORY_URL ="/29_0_0_0_heat/";

   /**
    * @Description 抓取书籍内容
    * @author liuhu
    * @Date 2020/12/15 20:41
    */
    @Scheduled(fixedDelay = 24 * 60 * 60 * 1000)
    public void bookContentTask() throws Exception {
        String url = BOOK_URL+CATEGORY_URL;
        String html = hus.doGetHtml(url);
        Document doc = Jsoup.parse(html);
        Elements categoryElements = doc.select("div.wrapper > div.box-center.sx-wp.clear > div.sx-cate.fl > ul.cate-list.clear >li");
        //所有分类
        for (Element element : categoryElements) {
            String categoryUrl = element.select("a").attr("href");
            //由于数量太多 暂定每一个种类爬5页
            String pageSize = "3";
//            String pageSize = doc.select("#pagination > a:nth-child(7)").text();
            // 所有分页
            for (int pageNo = 1; pageNo < Integer.parseInt(pageSize); pageNo++) {
                url = BOOK_URL+categoryUrl+"?pageNo="+pageNo;
                html = hus.doGetHtml(url);
                parseBookContentCategory(html);
                log.info("第【{}】页书籍抓取完成.............",pageNo);
            }
        }
        log.info("书籍抓取完成.............");
    }
    /**
     * @description 解析小说信息
     * @author liuhu
     * @param html
     * @date 2020/12/16 9:45
     * @return void
     */
    private void parseBookContentCategory(String html) {
        Document doc = Jsoup.parse(html);
        String categoryName = doc.select("div.wrapper > div.box-center.sx-wp.clear > div.sx-ret.fr > div.row.selected > span.flwp >a").attr("pbtag");
        // 书籍类别
        BookCategory bookCategory = categoryService.selectByName(categoryName);
        Elements elements = doc.select("div.wrapper > div.box-center.sx-wp.clear > div.sx-ret.fr > ul > li");
        // 书籍 作者 简介等信息
        for (Element element : elements) {
            String authorName = element.select("div>div.d1").text();
            authorName =authorName.substring(authorName.indexOf("：")+1,authorName.indexOf(" 更新章节"));
            // 1.保存作者信息
            Author author = saveAuthorInfo(authorName);
            // 2.保存书籍信息
            Book book = saveBookInfo(element, author, bookCategory);
            // 章节链接
            String chapterHref = element.select("div>div.btns>a.btn.btn-toc").attr("href");
            // 章节html
            String chapterHtml = hus.doGetHtml(BOOK_URL + chapterHref);
            Document document = Jsoup.parse(chapterHtml);
            Elements chapterElement = document.select("div.wrapper.mulu-wp > div.box-center.box-content.clear > div.chapter-box >ul >li");
            // 遍历所有章节
            for (Element chapterElementPer : chapterElement) {
                // 3.保存书籍章节信息
                saveBookChapterInfo(chapterElementPer,book);
                try {
                    Thread.sleep(5*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            log.info("抓取书籍：【{}】成功......",book.getName());
        }
    }

    /**
     * @Description 抓取书籍分类
     * @author liuhu
     * @Date 2020/12/15 20:41
     */
//    @Scheduled(fixedDelay = 24 * 60 * 60 * 1000)
    public void categoryTask() throws Exception {
        String url = "https://xiaoshuo.sogou.com/29_0_0_0_heat/";
        String html = hus.doGetHtml(url);
        parseCategory(html);
        log.info("书籍分类抓取完成.............");
    }

    private void parseCategory(String html) {
        try {
            Document doc = Jsoup.parse(html);
            Elements elements = doc.select("div.wrapper > div.box-center.sx-wp.clear > div.sx-cate.fl >ul.cate-list.clear");
            for (Element element : elements) {
                String workDirectionStr = element.attr("pbflag");
                Elements li = element.select("li");
                for (Element elementLi : li) {
                    // 分类名称
                    String name = elementLi.select("a").text();
                    BookCategory category = new BookCategory();
                    category.setName(name);
                    category.setWorkDirection(StringUtils.equals(workDirectionStr, BookConstants.MAN_WORK_DIRECTION) ? 1 : 2);
                    categoryService.saveBookCategory(category);
                }
            }
        } catch (Exception e) {
            log.error("解析书籍分类HTML失败！");
            throw new BookException("解析书籍分类HTML失败！");
        }
    }

    /**
     * @description 保存书籍信息
     * @author liuhu
     * @param bookElement
     * @param author
     * @param bookCategory
     * @date 2020/12/16 9:15
     * @return void
     */
    private Book saveBookInfo(Element bookElement,Author author,BookCategory bookCategory){
        String bookName = bookElement.select("div>h3>a").text();
        String imageUrl = bookElement.select("a.cover.fl>img").attr("src");
        Book select = bookService.selectByBookName(bookName);
        if(null == select){
            Attachment upload = hus.upload("http:" + imageUrl);
            String imgUrl =upload.getDomain()+"/"+upload.getBucket()+"/"+upload.getName();
            String bookDescription = bookElement.select("div>div.d2").text();
            // 全书总字数
            String totalText = bookElement.select("div.wrapper.mulu-wp > div.box-center.box-content.clear > div.info-tit.volume > span.d").text();
            Book book = Book.builder().name(bookName).authorName(author.getPenName()).authorId(author.getId())
                    .categoryName(null != bookCategory ? bookCategory.getName() : null)
                    .categoryId(null != bookCategory ? bookCategory.getId() : null)
                    .bookDescription(bookDescription).totalTextCount(totalText)
                    .imageUrl(imgUrl)
                    .attachmentId(upload.getId())
                    .build();
            bookService.saveBook(book);
            return book;
        }else {
            return select;
        }
    }

    /**
     * @description 保存作者信息
     * @author liuhu
     * @param authorName
     * @date 2020/12/16 9:29
     * @return com.motang.motangge.entity.Author
     */
    private Author saveAuthorInfo(String authorName){
        Author select = authorService.selectByName(authorName);
       if(null == select){
           Author author = Author.builder().penName(authorName).build();
           authorService.saveAuthor(author);
           return author;
       }else {
           return select;
       }
    }
    /**
     * @description 保存章节和正文
     * @author liuhu
     * @param chapterElementPer
     * @param book
     * @date 2020/12/16 9:40
     * @return void
     */
    private void saveBookChapterInfo(Element chapterElementPer,Book book){
        String chapterName = chapterElementPer.select("a>span").text();
        BookChapter select =  chapterService.selectByName(chapterName);
       if(null == select){
            // 正文链接
            String contentHref = chapterElementPer.select("a").attr("href");
            String contentHtml = hus.doGetHtml(BOOK_URL + contentHref);
            Document contentDocument = Jsoup.parse(contentHtml);
            String chapterDescription = contentDocument.select("div.reader-main > div.paper-box.paper-article > div.info").text();
            String content = contentDocument.select("#contentWp").text();
            // 保存章节信息
            BookChapter bookChapter = BookChapter.builder().name(chapterName).chapterDescription(chapterDescription).bookId(book.getId()).build();
            chapterService.saveChapter(bookChapter);
            // 4.保存书籍正文信息
            BookContent bookContent = BookContent.builder().chapterId(bookChapter.getId()).content(content).build();
            bookContentService.saveBookContent(bookContent);
        }
    }
}

