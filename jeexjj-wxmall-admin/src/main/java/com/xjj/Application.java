package com.xjj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author zhanghejie
 */
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.xjj.**.dao")
@ComponentScan("com.xjj")
public class Application extends SpringBootServletInitializer{
	
	/**
	 * 导出war在外部tomcat使用时, 不能使用main函数运行, 需要配置此项
	 */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Application.class);
    }

    /**
     * IDE运行时 运行此函数
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}