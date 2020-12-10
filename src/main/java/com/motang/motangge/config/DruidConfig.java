package com.motang.motangge.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
  
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description druid监控配置
 * @author liuhu
 * @Date 2020/12/10 19:51
 */
@Configuration
public class DruidConfig {

  /**
   * @description  加载yml配置
   * @author liuhu
   * @date 2020/12/10 19:52
   * @return javax.sql.DataSource
   */
  @ConfigurationProperties(prefix = "spring.datasource")
  @Bean
  public DataSource druid(){
    return new DruidDataSource();
  }
  
  /**
   * @description druid监控配置
   * @author liuhu
   * @date 2020/12/10 19:50
   * @return org.springframework.boot.web.servlet.ServletRegistrationBean
   */
  @Bean
  public ServletRegistrationBean statViewServlet(){
    ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
    Map<String,String> initParams = new HashMap<>();
    initParams.put("loginUsername","admin");
    initParams.put("loginPassword","123456");
    initParams.put("allow","");//默认就是允许所有访问
    initParams.put("deny","");
    bean.setInitParameters(initParams);
    return bean;
  }
  
  
  /**
   * @description web监控的filter
   * @author liuhu
   * @date 2020/12/10 19:51
   * @return org.springframework.boot.web.servlet.FilterRegistrationBean
   */
  @Bean
  public FilterRegistrationBean webStatFilter(){
    FilterRegistrationBean bean = new FilterRegistrationBean();
    bean.setFilter(new WebStatFilter());
    Map<String,String> initParams = new HashMap<>();
    initParams.put("exclusions","*.js,*.css,/druid/*");
    bean.setInitParameters(initParams);
    bean.setUrlPatterns(Arrays.asList("/*"));
    return bean;
  }
}