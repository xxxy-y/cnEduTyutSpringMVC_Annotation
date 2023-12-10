package cn.edu.tyut.controller;

import cn.edu.tyut.entity.User;
import com.alibaba.fastjson2.JSONObject;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Author 羊羊
 * @ClassName JsonString
 * @SubmitTime 周日
 * @DATE 2023/12/10
 * @Time 16:07
 * @Package_Name cn.edu.tyut.controller
 */
@Controller
public class JsonString {
    /**
     * 如果设置了produces的值，将返回的内容类型设定为application/json，表示服务器端返回了一个JSON格式的数据（当然不设置也行，也能展示，但是展示的是JSON样式的字符串。这样是为了规范）
     * 然后我们在方法上添加一个@ResponseBody表示方法返回（也可以在类上添加@RestController表示此Controller默认返回的是字符串数据）的结果不是视图名称而是直接需要返回一个字符串作为页面数据，这样，返回给浏览器的就是我们直接返回的字符串内容。
     * 也可以直接返回一个对象，添加fastjson在Spring中的依赖，还需要配置可以转换JSON数据为JSON格式的消息类型转换器，那么便会自动转换为JSON字符串格式。
     *
     * @return 返回字符串
     */
    @ResponseBody
    @GetMapping(value = "/entityJsonString", produces = "application/json")
    public User entityJsonString() {
        User user = new User();
        user.setUsername("returnEntityString");
        user.setPassword("entityJsonString");
        return user;
    }

    /**
     * 需要返回json类型的数据格式，将数据传递到 axiosJson.html 页面，实现动态页面。
     */
    @ResponseBody
    @GetMapping("/axiosTest")
    public User axiosTest() {
        User user = new User();
        user.setUsername("axiosUsername");
        user.setPassword("axiosPassword");
        return user;
    }

    @GetMapping("/getAxios")
    public String getAxios() {
        System.out.println("访问到 axiosJson.html 页面");
        return "axiosJson";
    }

    @ResponseBody
    @PostMapping(value = "/axiosObjectTest01", produces = "application/json")
    public String hello(@NotNull User user){
        boolean success = "test".equals(user.getUsername()) && "123456".equals(user.getPassword());
        JSONObject object = new JSONObject();
        object.put("success", success);
        return object.toString();
    }

    @GetMapping("/getAxiosObject01")
    public String getAxiosObject01() {
        System.out.println("访问到 axiosJsonObject01.html 页面");
        return "axiosJsonObject01";
    }

    @ResponseBody
    @PostMapping(value = "/axiosObjectTest02", produces = "application/json")
    public String axiosJsonObject02(@NotNull User user){
        boolean success = "test".equals(user.getUsername()) && "123456".equals(user.getPassword());
        JSONObject object = new JSONObject();
        object.put("success", success);
        return object.toString();
    }

    @GetMapping("/getAxiosObject02")
    public String getAxiosObject02() {
        System.out.println("访问到 axiosJsonObject01.html 页面");
        return "axiosJsonObject02";
    }
}
