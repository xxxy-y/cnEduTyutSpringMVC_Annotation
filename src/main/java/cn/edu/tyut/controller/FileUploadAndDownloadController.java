package cn.edu.tyut.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * @Author 羊羊
 * @ClassName FileUploadAndDownloadController
 * @SubmitTime 周一
 * @DATE 2023/12/11
 * @Time 19:36
 * @Package_Name cn.edu.tyut.controller
 */
@Controller
public class FileUploadAndDownloadController {
    /**
     * MultipartFile 是 Spring 框架中用于处理 HTTP POST 请求中文件上传的类。它是一个接口，代表了上传的文件，包含了文件名、文件类型、文件大小以及文件内容等属性。
     * MultipartFile 可以用于以下目的：
     * 获取文件名
     * 获取文件类型
     * 获取文件大小
     * 获取文件内容
     * 保存文件
     * MultipartFile 的常用方法如下：
     * getName()：获取文件名
     * getContentType()：获取文件类型
     * getSize()：获取文件大小
     * getBytes()：获取文件内容
     * transferTo(File dest)：保存文件
     */
    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam @NotNull MultipartFile file) throws IOException {
        File fileObj = new File("test.png");
        file.transferTo(fileObj);
        System.out.println("用户上传的文件已保存到：C:\\code");
        return "文件上传成功！";
    }

    @GetMapping("/getUpload")
    public String getUpload() {
        return "upload";
    }

    @PostMapping("/download")
    @ResponseBody
    public void download(@NotNull HttpServletResponse response){
        response.setContentType("multipart/form-data");
        try(OutputStream stream = response.getOutputStream();
            InputStream inputStream = new FileInputStream("C:\\code\\test.png")){
            IOUtils.copy(inputStream, stream);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @GetMapping("/getDownload")
    public String getDownload() {
        return "download";
    }
}
