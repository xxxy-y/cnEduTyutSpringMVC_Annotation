package cn.edu.tyut.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author 羊羊
 * @ClassName firstController
 * @SubmitTime 周三
 * @DATE 2023/12/6
 * @Time 17:26
 * @Package_Name cn.edu.tyut.controller
 */
@Controller
public class FirstController {
    @RequestMapping("a")
    public void a() {
        System.out.println("success");
    }
}
