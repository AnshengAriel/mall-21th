package com.cskaoyan.mall.service.test_jiayi;

//import com.cskaoyan.config.AliyunComponent;
import com.cskaoyan.mall.config.AliyunComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;

@Service
@Transactional
public class FileServiceImpl implements FileService{

    @Autowired
    AliyunComponent aliyunComponent;

    public void fileUpload(String fileName, File file){
        aliyunComponent.fileUpload(fileName,file);

    }

    public void sendMsg(String phone,String code){

        aliyunComponent.sendMsg(phone,code);
    }
}
