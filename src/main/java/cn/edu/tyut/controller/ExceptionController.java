package cn.edu.tyut.controller;

import jdk.jfr.Experimental;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @Author 羊羊
 * @ClassName ExceptionController
 * @SubmitTime 周一
 * @DATE 2023/12/11
 * @Time 17:54
 * @Package_Name cn.edu.tyut.controller
 */
@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(Exception.class)
    public String error(@NotNull Exception e, @NotNull Model model) {
        e.printStackTrace();
        model.addAttribute("e", e);
        return "error";
    }
}