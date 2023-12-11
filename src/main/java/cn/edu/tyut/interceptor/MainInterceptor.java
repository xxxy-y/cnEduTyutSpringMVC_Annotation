package cn.edu.tyut.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author 羊羊
 * @ClassName MainInterceptor
 * @SubmitTime 周一
 * @DATE 2023/12/11
 * @Time 17:01
 * @Package_Name cn.edu.tyut.interceptor
 */
public class MainInterceptor implements HandlerInterceptor {
    /**
     * 处理前和处理后，包含了真正的请求映射的处理，在整个流程结束后还执行了一次afterCompletion方法，
     */
    @Override
    public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) throws Exception {
        System.out.println("一号拦截器：我是处理之前...");
        return true;
    }

    @Override
    public void postHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("一号拦截器：我是处理之后...");
    }

    /**
     * 在处理器完全处理完请求后被调用
     *
     * @param request  current HTTP request
     * @param response current HTTP response
     * @param handler  the handler (or {@link HandlerMethod}) that started asynchronous
     *                 execution, for type and/or instance examination
     * @param ex       any exception thrown on handler execution, if any; this does not
     *                 include exceptions that have been handled through an exception resolver
     */
    @Override
    public void afterCompletion(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler, @NotNull Exception ex) throws Exception {
        System.out.println("一号拦截器：我是完成之后...");
        System.out.println(ex.getClass());
    }
}
