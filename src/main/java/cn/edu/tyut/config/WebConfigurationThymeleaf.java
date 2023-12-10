package cn.edu.tyut.config;

import com.alibaba.fastjson2.support.spring6.http.converter.FastJsonHttpMessageConverter;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.util.List;

/**
 * @Author 羊羊
 * @ClassName WebConfiguration
 * @SubmitTime 周三
 * @DATE 2023/12/6
 * @Time 17:22
 * @Package_Name cn.edu.tyut.cnedutyutspringmvc_annotatiion.config
 * EnableWebMvc注解是快速配置SpringMvc注解，如果不添加此注解会导致后续无法通过实现WebMvcConfigurer接口进行自定义配置
 */
@Configuration
@EnableWebMvc
@ComponentScans({@ComponentScan("cn.edu.tyut.controller")})
public class WebConfigurationThymeleaf implements WebMvcConfigurer {
    /**
     * 使用ThymeleafViewResolver作为视图解析器来解析我们的HTML页面
     * 可以存在多个解析器，但是需要为他们通过setOrder(1)来设定解析顺序
     * setCharacterEncoding("UTF-8")设置编码格式
     * 视图解析器是通过模板引擎来进行解析的，所以这里也需要设定一下模板引擎
     *
     * @param springTemplateEngine 传入的模板引擎
     * @return 返回视图解析器
     */
    @Bean
    public ThymeleafViewResolver thymeleafViewResolver(SpringTemplateEngine springTemplateEngine) {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
//        resolver.setOrder(1);
//        resolver.setCharacterEncoding("UTF-8");
        resolver.setTemplateEngine(springTemplateEngine);
        return resolver;
    }

    /**
     * 配置模板引擎中的模板解析器为提供的参数类型
     *
     * @param resolver 模板解析器
     * @return 模板引擎
     */
    @Bean
    public SpringTemplateEngine springTemplateEngine(ITemplateResolver resolver) {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setTemplateResolver(resolver);
        return engine;
    }

    /**
     * 配置模板解析器中的资源前缀和后缀
     * 默认资源路径在webapp目录下，如果是类路径下需要添加classpath:前缀
     *
     * @return 模板解析器
     */
    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setPrefix("WEB-INF/pages/");
        resolver.setSuffix(".html");
        return resolver;
    }

    /**
     * 调用configureDefaultServletHandling方法启用默认的Servlet处理，这样可以确保静态资源能够正确处理。
     *
     * @param configurer 让静态资源通过Tomcat提供的默认Servlet进行解析，我们需要让配置类实现一下WebMvcConfigurer接口
     */
    @Override
    public void configureDefaultServletHandling(@NotNull DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addResourceHandlers(@NotNull ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
        //配置静态资源的访问路径
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new FastJsonHttpMessageConverter());
    }
}