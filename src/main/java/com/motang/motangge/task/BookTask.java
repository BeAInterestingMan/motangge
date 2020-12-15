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

@EnableScheduling
@Component
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

    private static final String BOOK_URL ="https://xiaoshuo.sogou.com";

   /**
    * @Description 抓取书籍内容
    * @author liuhu
    * @Date 2020/12/15 20:41
    */
    @Scheduled(fixedDelay = 24 * 60 * 60 * 1000)
    public void bookContentTask() throws Exception {
        String url = BOOK_URL+"/29_0_0_0_heat/";
        String html = hus.doGetHtml(url);
        parseBookContentCategory(html);
        log.info("书籍正文抓取完成.............");
    }

    private void parseBookContentCategory(String html) {
        Document doc = Jsoup.parse(html);
        String categoryName = doc.select("div.wrapper > div.box-center.sx-wp.clear > div.sx-ret.fr > div.row.selected > span.flwp >a").attr("pbtag");
        // 书籍类别
        BookCategory bookCategory = categoryService.selectByName(categoryName);
        Elements elements = doc.select("div.wrapper > div.box-center.sx-wp.clear > div.sx-ret.fr > ul > li");
        // 书籍 作者 简介等信息
        for (Element element : elements) {
            String bookName = element.select("div>h3>a").text();
            String authorName = element.select("div>div.d1").text();
            // 1.保存作者信息
            Author author = Author.builder().penName(authorName).build();
            authorService.saveAuthor(author);
            String bookDescription = element.select("div>div.d2").text();
            // 章节链接
            String chapterHref = element.select("div>div.btns>a.btn.btn-toc").attr("href");
            // 章节html
            String chapterHtml = hus.doGetHtml(BOOK_URL + chapterHref);
            Document document = Jsoup.parse(chapterHtml);
            Elements chapterElement = document.select("div.wrapper.mulu-wp > div.box-center.box-content.clear > div.chapter-box >ul >li");
            // 全书总字数
            String totalText = document.select("body > div.wrapper.mulu-wp > div.box-center.box-content.clear > div.info-tit.volume > span.d").text();
            // 2.保存书籍信息
            Book book = Book.builder().name(bookName).authorName(authorName).authorId(author.getId())
                    .categoryName(null != bookCategory ? bookCategory.getName() : null)
                    .categoryId(null != bookCategory ? bookCategory.getId() : null)
                    .bookDescription(bookDescription).totalTextCount(totalText).build();
            bookService.saveBook(book);
            // 遍历所有章节
            for (Element chapterElementPer : chapterElement) {
                String chapterName = chapterElementPer.select("a>span").text();
                // 正文链接
                String contentHref = chapterElementPer.select("a").attr("href");
                String contentHtml = hus.doGetHtml(BOOK_URL + contentHref);
                Document contentDocument = Jsoup.parse(contentHtml);
//                String createTime = contentDocument.select("div.reader-main > div.paper-box.paper-cover > p:nth-child(5)").text();
                String chapterDescription = contentDocument.select("div.reader-main > div.paper-box.paper-article > div.info").text();
                String content = contentDocument.select("#contentWp").text();
                // 3.保存书籍章节信息
                BookChapter bookChapter = BookChapter.builder().name(chapterName).chapterDescription(chapterDescription).bookId(book.getId()).build();
                chapterService.saveChapter(bookChapter);
                // 4.保存书籍正文信息
                BookContent bookContent = BookContent.builder().chapterId(bookChapter.getId()).content(content).build();
                bookContentService.saveBookContent(bookContent);
                try {
                    Thread.sleep(30*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("抓取【】成功......",bookName);
            }

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
}

