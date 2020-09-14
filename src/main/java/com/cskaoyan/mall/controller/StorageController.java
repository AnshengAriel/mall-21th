package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.BaseRespVo;
import com.cskaoyan.mall.bean.bean_of_hanyuandong.Storage;
import com.cskaoyan.mall.bean.bean_of_hanyuandong.StorageListVo;
import com.cskaoyan.mall.service.StorageService;
import com.cskaoyan.mall.utils.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("admin/storage/")
public class StorageController {

    @Autowired
    StorageService storageService;

    @RequestMapping("list")
    public BaseRespVo list(Integer page,Integer limit,String sort,String order) {
        StorageListVo storageListVo = storageService.queryStorageList(page,limit,sort,order);
        return BaseRespVo.ok(storageListVo);
    }

    @RequestMapping(value = "list", params = "name")
    public BaseRespVo searchByName(Integer page,Integer limit,String sort,String order,String name) {
        StorageListVo storageListVo = storageService.queryStorageListByName(page,limit,sort,order,name);
        return BaseRespVo.ok(storageListVo);
    }

    @RequestMapping(value = "list", params = "key")
    public BaseRespVo searchByKey(Integer page,Integer limit,String sort,String order,String key) {
        StorageListVo storageListVo = storageService.queryStorageListByKey(page,limit,sort,order,key);
        return BaseRespVo.ok(storageListVo);
    }

    @RequestMapping(value = "list", params = {"key","name"})
    public BaseRespVo searchByKeyAndName(Integer page,Integer limit,String sort,String order,String key,String name) {
        StorageListVo storageListVo = storageService.queryStorageListByKeyAndName(page,limit,sort,order,key,name);
        return BaseRespVo.ok(storageListVo);
    }

    @PostMapping("create")
    public BaseRespVo create(MultipartFile file) {
        //文件上传
        UploadUtil.fileUpload(file);
        Storage storage = storageService.createStorage(file);
        return BaseRespVo.ok(storage);
    }

    @PostMapping("update")
    public BaseRespVo update(@RequestBody Storage storage) {
        Storage storage1 = storageService.updateStorage(storage);
        return BaseRespVo.ok(storage1);
    }

    @PostMapping("delete")
    public BaseRespVo delete(@RequestBody Storage storage) {
        storageService.logicDeleteStorage(storage);
        return BaseRespVo.ok();
    }
}
