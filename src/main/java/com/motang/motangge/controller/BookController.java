package com.motang.motangge.controller;


import com.motang.motangge.entity.Book;
import com.motang.motangge.service.IBookService;
import io.minio.MinioClient;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @Description 书籍表 前端控制器
 * @author liuhu
 * @Date 2020/12/10 21:07
 */
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private IBookService bookService;

    @Autowired
    private MinioClient minioClient;

    @GetMapping("upload")
    public void upload(){
        try {
            minioClient.putObject("book","test.jpg","D:\\lh\\test.jpg");
        } catch (InvalidBucketNameException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("image")
    public String image(){
        try {
        return     minioClient.presignedGetObject("book","test.jpg");
        } catch (InvalidBucketNameException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("{id}")
    public Book book (@PathVariable("id")Long id){
        return bookService.selectOne(id);
    }
}
