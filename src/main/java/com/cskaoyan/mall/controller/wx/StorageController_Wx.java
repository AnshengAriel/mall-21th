package com.cskaoyan.mall.controller.wx;

import com.cskaoyan.mall.bean.BaseRespVo;
import com.cskaoyan.mall.bean.bean_of_hanyuandong.Storage;
import com.cskaoyan.mall.service.StorageService;
import com.cskaoyan.mall.utils.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("wx/storage/")
public class StorageController_Wx {

    @Autowired
    StorageService storageService;

    @PostMapping("upload")
    public BaseRespVo create(MultipartFile file) {
        //文件上传
        UploadUtil.fileUpload(file);
        Storage storage = storageService.createStorage(file);
        return BaseRespVo.ok(storage);
    }
}
