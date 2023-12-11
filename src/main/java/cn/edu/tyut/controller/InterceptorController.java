package cn.edu.tyut.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author 羊羊
 * @ClassName InterceptorController
 * @SubmitTime 周一
 * @DATE 2023/12/11
 * @Time 17:14
 * @Package_Name cn.edu.tyut.controller
 */
@Controller
public class InterceptorController {
    @RequestMapping("/interceptor")
    public String interceptorController() {
        System.out.println("处理中...");
        if(true) {
            throw new RuntimeException("");
        }
        return "error";
    }
}