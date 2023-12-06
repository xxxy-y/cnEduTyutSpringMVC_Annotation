package cn.edu.tyut.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @Author 羊羊
 * @ClassName WebConfiguration
 * @SubmitTime 周三
 * @DATE 2023/12/6
 * @Time 17:22
 * @Package_Name cn.edu.tyut.cnedutyutspringmvc_annotatiion.config
 * EnableWebMvc注解是为了快速配置SpringMvc注解，如果不添加此注解会导致后续无法通过实现WebMvcConfigurer接口进行自定义配置
 */
@Configuration
@EnableWebMvc
@ComponentScans({
        @ComponentScan("cn.edu.tyut.controller")
})
public class WebConfiguration {

}
