package com.cskaoyan.mall.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Component
public class UploadUtil {

    /**
     * 文件上传
     * @param file
     */
    public static void fileUpload(MultipartFile file) {
        String filename = file.getOriginalFilename();
        String filePath = "E:\\develop\\fileUpload\\";
        File dest = new File(filePath + filename);

        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
