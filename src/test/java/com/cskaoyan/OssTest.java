package com.cskaoyan;


import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.UUID;

public class OssTest {

    @Test
    public void mytest() throws JsonProcessingException {
        String accessKeyId = "LTAI4Fr5gfYhcVjLMqeRGbuT";
        String accessKeySecret = "IrkcHu6dZyrjPZRushgO76P5392HJ1";
        String endPoint = "oss-cn-beijing.aliyuncs.com";
        String bucket = "cskaoyan";

        //准备个文件
        File file = new File("C:\\Users\\Administrator\\Desktop", "logo.png");
        //FileInputStream fileInputStream = new FileInputStream(file);
        String fileName = UUID.randomUUID().toString().replaceAll("-","") + ".png";
        System.out.println(fileName); //上传完成 要通过bucket+endpoint+文件名进行访问

        OSSClient ossClient = new OSSClient(endPoint, accessKeyId, accessKeySecret);


        /*MultipartFile myfile;
        InputStream inputStream = myfile.getInputStream();*/

        PutObjectResult putObjectResult = ossClient.putObject(bucket, fileName, file);

        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(putObjectResult);
        System.out.println(s);

    }
}
