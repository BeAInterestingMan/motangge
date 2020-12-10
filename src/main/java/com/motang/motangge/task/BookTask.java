package com.motang.motangge.task;

import com.motang.motangge.entity.Book;
import com.motang.motangge.mapper.BookMapper;
import com.motang.motangge.service.IBookService;
import com.motang.motangge.utils.HttpUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableScheduling
@Component
public class BookTask {

    @Autowired
    private HttpUtils hus;

    @Autowired
    private BookMapper bookMapper;
    /**
     * 每隔多久执行一次任务
     * 定时任务
     * @throws Exception
     */
    //每隔多久执行一次任务
    @Scheduled(fixedDelay = 100000*1000)
    public void itemTask()throws Exception{
        //声明需要解析的初始地址 https://search.jd.com/Search?keyword=%E6%89%8B%E6%9C%BA&wq=%E6%89%8B%E6%9C%BA&page=1&s=1&click=0
//        https://www.qidian.com/all?chanId=21&action=1&orderId=&page=1&vip=0&style=1&pageSize=20&siteid=1&pubflag=0&hiddenField=0   完本玄幻
//        https://www.qidian.com/all?chanId=22&action=1&orderId=&page=1&vip=0&style=1&pageSize=20&siteid=1&pubflag=0&hiddenField=0
//        String url ="https://www.qidian.com/all?chanId=21&subCateId=8";
//        String url ="https://www.qidian.com/all?chanId=5&orderId=&page=1&style=1&pageSize=20&siteid=1&pubflag=0&hiddenField=0";
//        for(int i = 1;i < 3 ;i ++){
//            String html = hus.doGetHtml(url+i);
//            //解析页面获取商品数据并存储
//            this.parse(html);
//        }

        String url ="http://www.xyusk.com/xuanhuan_2.html";
        String html = hus.doGetHtml(url);
        je(html);
//        String html = hus.doGetHtml(url);
        //解析页面获取商品数据并存储
//        this.parse(html);
        System.out.println("数据抓取完成");
    }

    private void je(String html) {
        Document doc =  Jsoup.parse(html);
        Elements els = doc.select("div.up > div > ul > li");
        for (Element lis : els) {
            String  bookName = lis.select(".s2 > a").text();
            String  lastBook = lis.select(".s3 > a").text();
            String  author = lis.select(".s4").text();

            Book book = Book.builder().authorName(author).name(bookName).status(1).build();
            bookMapper.insert(book);
//            String titleHref = lis.select(".s2 > a").attr("href");
//            String bookhtml = hus.doGetHtml("http://www.xyusk.com/"+titleHref);
//            Document bookdoc =  Jsoup.parse(bookhtml);
//            Elements zhangjie = bookdoc.select("div.listmain > dl");
//            for (Element element : zhangjie) {
//                String zhangjieName = element.select("dd").text();
//                String href = element.select("dd > a").attr("href");
//                String sa = hus.doGetHtml("http://www.xyusk.com/"+titleHref+href);
//                Document xx =  Jsoup.parse(sa);
//                String text = xx.select("div > .content > .showtxt").text();
//                System.out.println(text);
//            }
//            String  xx = lis.select(".s4").text();


        }
    }
}
