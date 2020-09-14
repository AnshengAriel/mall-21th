package com.cskaoyan.mall.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Component
public class UploadUtil2 {

    /**
     * 文件上传
     * @param file
     */
    public static String fileUpload(MultipartFile file) {
        String filename = file.getOriginalFilename(); //timg.jpg
        String substring = filename.substring(filename.lastIndexOf(".")); //.jpg
         String filePath = "E:\\develop\\fileUpload\\";  //真正开发的时候不要放在classpath 的路径下，最后会导成jar包，没有Idea帮助的话就不能运行
//        String property = System.getProperty("user.dir");
//        property+="\\src\\main\\resources\\static\\picture\\"; //当前指向picture文件夹
//        System.out.println(property);
//        String filePath = property;
        File dest = new File(filePath + UUID.randomUUID().toString() + substring);
        try {
            file.transferTo(dest); //将file 源文件转存到 目标文件dest
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dest.getName();
    }
}