package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.UUID;

@Controller
public class UploadController {

    @RequestMapping("/index")
    public String toUpload(){
        return "upload";
    }

    @PostMapping("/uploads")
    @ResponseBody
    public String uploadFile(MultipartFile file, HttpServletRequest request){
        try {
            //创建文件在服务器端的存放路径
            String dir = request.getServletContext().getRealPath("/upload");
            File fileDir = new File(dir);
            if(!fileDir.exists()){
                fileDir.mkdir();
            }
            //生成文件在服务器端存放的名字
            String substring = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            String fileName = UUID.randomUUID().toString() + substring;
            File files = new File(fileDir+"/"+fileName);
            //上传
            file.transferTo(files);
        } catch (Exception e) {
            e.printStackTrace();
            return "上传失败";
        }
        return "上传成功";
    }
}
