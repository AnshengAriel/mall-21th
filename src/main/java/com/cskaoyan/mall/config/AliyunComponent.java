package com.cskaoyan.mall.config;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectResult;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.InputStream;

@Component
@ConfigurationProperties(prefix = "aliyun")
@Data
public class AliyunComponent {

    String accessKeyId;
    String accessKeySecret;
    Oss oss;
    Sms sms;

    public OSSClient getOssClient(){
        OSSClient ossClient = new OSSClient(oss.getEndPoint(), accessKeyId, accessKeySecret);
        return ossClient;

    }
    public PutObjectResult fileUpload(String fileName, File file){
        OSSClient ossClient = getOssClient();
        PutObjectResult putObjectResult = ossClient.putObject(oss.getBucket(), fileName, file);
        return putObjectResult;
    }
    public PutObjectResult fileUpload(String fileName, InputStream inputStream){
        OSSClient ossClient = getOssClient();
        PutObjectResult putObjectResult = ossClient.putObject(oss.getBucket(), fileName, inputStream);
        return putObjectResult;
    }

    public void sendMsg(String phoneNumber,String code){
        String signName = sms.getSignName();
        String templateCode = sms.getTemplateCode();

        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");

        request.putQueryParameter("PhoneNumbers", phoneNumber);


        request.putQueryParameter("SignName", signName);
        request.putQueryParameter("TemplateCode", templateCode);
        request.putQueryParameter("TemplateParam", "{\"code\":\""+code+"\"}");
        CommonResponse response = null;
        try {
            response = client.getCommonResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        System.out.println(response.getData());

    }


}
