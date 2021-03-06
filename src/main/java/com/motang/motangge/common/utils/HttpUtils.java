package com.motang.motangge.common.utils;

import com.motang.motangge.common.constant.BookConstants;
import com.motang.motangge.entity.Attachment;
import com.motang.motangge.service.IAttachmentService;
import io.minio.MinioClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

@Component
public class HttpUtils {
    //声明连接池管理器
    private PoolingHttpClientConnectionManager cm;

    @Autowired
    private MinioClient minioClient;

    @Value("${minio.url}")
    private String domainUrl;

    @Value("${minio.bucket}")
    private String bucket;

    @Autowired
    private IAttachmentService attachmentService;

    public HttpUtils() {
        this.cm = new PoolingHttpClientConnectionManager();
        //设置最大连接数
        cm.setMaxTotal(100);
        //设置每个主机的连接数
        cm.setDefaultMaxPerRoute(10);
    }

    //使用get请求获取页面数据

    /**
     * 使用get请求获取页面数据
     * @param url
     * @return 页面数据
     */
    public String doGetHtml(String url){
        //获取httpClinet对象
        CloseableHttpClient httpClient =  HttpClients.custom().setConnectionManager(this.cm).build();
        //设置httpget请求对象，设置url地址
        HttpGet HG = new HttpGet(url);
        HG.setHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9," +
                "image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        HG.setHeader("accept-encoding","gzip, deflate, br");
        HG.setHeader("accept-language","zh-CN,zh;q=0.9");
        HG.setHeader("cookie","");
        HG.setHeader("user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 " +
                "(KHTML, like Gecko) Chrome/85.0.4183.121 Safari/537.36");
        //设置请求信息
        HG.setConfig(this.getConfig());
        CloseableHttpResponse response = null;
        try {
            //设置HttpClient发起请求 获取响应
            response = httpClient.execute(HG);

            //解析响应，返回结果
            if (response.getStatusLine().getStatusCode() == 200){
                //判断响应体是否为null  不是则能使用EndityUtils
                if (response.getEntity() != null){
                    String con =EntityUtils.toString(response.getEntity(),"UTF-8");
                    return con;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //关闭response
            if (response !=null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }



    /**
     * @description  下载图片
     * @author liuhu
     * @param url 图片地址
     * @date 2020/12/10 21:22
     * @return java.lang.String
     */
    public Attachment upload(String url){
        Attachment attachment = null;
        //获取httpClinet对象
        CloseableHttpClient httpClient =  HttpClients.custom().setConnectionManager(this.cm).build();
        //设置httpget请求对象，设置url地址
        System.out.println("下载地址："+url);
        if(url.length() <1){
            return attachment;
        }
        HttpGet HG = new HttpGet(url);
        //设置请求信息
        HG.setConfig(this.getConfig());
        CloseableHttpResponse response = null;
        try {
            //设置HttpClient发起请求 获取响应
            response = httpClient.execute(HG);

            //解析响应，返回结果
            if (response.getStatusLine().getStatusCode() == 200){
                //判断响应体是否为null
                if (response.getEntity() != null){
                    //获取图片后缀
                    String suffix = url.substring(url.lastIndexOf("."));
                    //重命名图片
                    String fileName = UUID.randomUUID().toString() + suffix;
                    String localFileName = BookConstants.LOCAL_FILE_STORE + fileName;
                    //创建一个文件
                    File file = new File(localFileName);
                    //声明输出流
                    OutputStream os = new FileOutputStream(file);
                    // 写入
                    response.getEntity().writeTo(os);
                    // 上传到minio
                    minioClient.putObject(bucket,fileName,localFileName);
                     attachment = Attachment.builder()
                                            .domain(domainUrl)
                                            .bucket(bucket)
                                            .fileSize(file.length())
                                            .name(fileName)
                                            .type(suffix)
                                            .build();
                    attachmentService.saveAttachment(attachment);
                    file.delete();
                    return attachment;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //关闭response
            if (response !=null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return attachment;
    }

    /**
     * @description  下载图片
     * @author liuhu
     * @param url 图片地址
     * @date 2020/12/10 21:22
     * @return java.lang.String
     */
    public String downImg(String url){
        //获取httpClinet对象
        CloseableHttpClient httpClient =  HttpClients.custom().setConnectionManager(this.cm).build();
        //设置httpget请求对象，设置url地址
        System.out.println("下载地址："+url);
        if(url.length() <1){
            return "";
        }
        HttpGet HG = new HttpGet(url);
        //设置请求信息
        HG.setConfig(this.getConfig());
        CloseableHttpResponse response = null;
        try {
            //设置HttpClient发起请求 获取响应
            response = httpClient.execute(HG);

            //解析响应，返回结果
            if (response.getStatusLine().getStatusCode() == 200){
                //判断响应体是否为null  不是则能使用EndityUtils
                if (response.getEntity() != null){
                    //下载图片
                    //获取图片后缀
                    String exname = url.substring(url.lastIndexOf("."));
                    //重命名图片
                    String imName = UUID.randomUUID().toString() + exname;
                    //下载图片
                    //声明outputstream
                    OutputStream os = new FileOutputStream(new File("D:\\lh\\image\\"+imName));
                    response.getEntity().writeTo(os);
                    //返回图片名称
                    return imName;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //关闭response
            if (response !=null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }


    private RequestConfig getConfig() {
        RequestConfig rf = RequestConfig.custom()
                .setConnectTimeout(1000)//创建连接的最长时间
                .setConnectionRequestTimeout(500)//获取连接的最长时间
                .setSocketTimeout(10000)//数据传输的最长时间
                .build();
        return rf;
    }
}
