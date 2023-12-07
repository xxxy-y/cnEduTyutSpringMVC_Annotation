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
     * 当处理器方法的参数中包含实体类型的参数时，是将请求参数包装为该实体类，通过Spring的依赖注入
     * 当处理器方法的参数中包含Model类型的参数时，这个Model参数其实是用来向视图传递数据的，而不是从请求参数中获取数据。
     * <br/>
     * 在Spring MVC中，Model是一个接口，用于存储和传递数据给视图。它允许在处理器方法中设置属性，并将这些属性传递给视图进行渲染。
     * <br/>
     * 当处理器方法的参数中包含Model类型的参数时，Spring会自动创建一个Model对象，并将其传递给处理器方法。我们可以在处理器方法中通过操作Model对象来设置属性，这些属性的值会被传递给视图。
     * <br/>
     * 处理器方法可以通过调用Model对象的方法来设置属性，例如addAttribute、setAttribute等。这些属性可以在视图中使用，以完成数据的展示和渲染。
     * <br/>
     * 需要注意的是，Model对象的作用域是当前请求的生命周期内，即在当前请求中设置的属性只对当前请求有效，不会影响其他请求。
     * <br/>
     * 所以，当处理器方法的参数中包含Model类型的参数时，并不是从请求参数中获取数据，而是用来向视图传递数据的。
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