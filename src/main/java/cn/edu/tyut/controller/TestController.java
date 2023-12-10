package cn.edu.tyut.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author 羊羊
 * @ClassName TestController
 * @SubmitTime 周五
 * @DATE 2023/12/8
 * @Time 11:47
 * @Package_Name cn.edu.tyut.controller
 */
@Controller
public class TestController {
    @RequestMapping("/test")
    public String test() {
        return "test";
    }
}
