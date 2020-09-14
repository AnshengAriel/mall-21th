package com.cskaoyan.mall.service;


import com.cskaoyan.mall.bean.bean_of_hanyuandong.Storage;
import com.cskaoyan.mall.bean.bean_of_hanyuandong.StorageListVo;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    StorageListVo queryStorageList(Integer page, Integer limit, String sort, String order);
    StorageListVo queryStorageListByName(Integer page, Integer limit, String sort, String order,String name);

    Storage createStorage(MultipartFile file);

    Storage updateStorage(Storage storage);

    void logicDeleteStorage(Storage storage);

    StorageListVo queryStorageListByKey(Integer page, Integer limit, String sort, String order, String key);

    StorageListVo queryStorageListByKeyAndName(Integer page, Integer limit, String sort, String order, String key, String name);

    Storage createFile(MultipartFile file,String newFlieName);

}
