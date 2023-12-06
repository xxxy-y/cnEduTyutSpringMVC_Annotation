package cn.edu.tyut.controller;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author 羊羊
 * @ClassName HelloController
 * @SubmitTime 周三
 * @DATE 2023/12/6
 * @Time 19:47
 * @Package_Name cn.edu.tyut.controller
 */
@Controller
public class HelloController {
    @RequestMapping("/index01")
    public ModelAndView index01() {
        return new ModelAndView("index01");
    }

    @RequestMapping("/index02")
    public ModelAndView index02() {
        ModelAndView modelAndView = new ModelAndView("index02");
        modelAndView.getModel().put("name", "index02");
        return modelAndView;
    }

    /**
     * 我们可以直接返回View名称，SpringMVC中的视图解析器会将其自动包装为ModelAndView对象
     * @return 直接返回视图的名称，
     */
    @RequestMapping("/index03")
    public String index03() {
        return "index03";
    }

    /**
     * 单独添加一个Model作为形参进行设置，SpringMVC通过Spring的依赖注入会自动帮助我们传递实例对象
     * 但是要给需要自动注入的参数添加@ModelAttribute注解（或者使用@NotNull注解）
     * @param model 形参自动注入
     * @return 返回视图
     */
    @RequestMapping("/index04")
    public String index04(@NotNull Model model) {
        model.addAttribute("name", "using index04");
        return "index04";
    }

    @RequestMapping("/index05")
    public String index05() {
        return "index05";
    }
}