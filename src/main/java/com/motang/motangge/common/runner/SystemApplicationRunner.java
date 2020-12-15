package com.motang.motangge.common.runner;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description springboot启动器
 * @author liuhu
 * @Date 2020/12/11 15:02
 */
@Configuration
public class SystemApplicationRunner implements ApplicationRunner {

    @Value("${minio.url}")
    private String minIoUrl;

    @Value("${minio.accessKey}")
    private String accessKey;

    @Value("${minio.secretKey}")
    private String secretKey;

    /**
     * @description 初始化minio配置
     * @author liuhu
     * @date 2020/12/11 15:01
     * @return io.minio.MinioClient
     */
    @Bean
    public MinioClient minioClient() {
        try {
            return new MinioClient(minIoUrl, accessKey, secretKey);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("minio加载失败");
        }
    }
    @Override
    public void run(ApplicationArguments args) throws Exception {

    }
}
