package com.cskaoyan.mall.service.impl;


import com.cskaoyan.mall.bean.bean_of_hanyuandong.Storage;
import com.cskaoyan.mall.bean.bean_of_hanyuandong.StorageExample;
import com.cskaoyan.mall.bean.bean_of_hanyuandong.StorageListVo;
import com.cskaoyan.mall.mapper.StorageMapper;
import com.cskaoyan.mall.service.StorageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;


import java.util.List;
import java.util.UUID;

@Service
public class StorageServiceImpl implements StorageService {


    @Autowired
    StorageMapper storageMapper;

    @Override
    public StorageListVo queryStorageList(Integer page, Integer limit, String sort, String order) {
        //先分页
        PageHelper.startPage(page,limit);
        //创建一个排序的条件
        StorageExample storageExample = new StorageExample();
        storageExample.setOrderByClause(sort + " " + order);
        storageExample.createCriteria().andDeletedEqualTo(false);

        return getStorageListVo(storageExample);
    }

    private StorageListVo getStorageListVo(StorageExample storageExample) {
        List<Storage> storageList = storageMapper.selectByExample(storageExample);
        //获取总条目数
        PageInfo<Storage> pageInfo = new PageInfo<>(storageList);
        long total = pageInfo.getTotal();
        StorageListVo storageListVo = new StorageListVo();
        storageListVo.setItems(storageList);
        storageListVo.setTotal(total);
        return storageListVo;
    }

    @Override
    public StorageListVo queryStorageListByName(Integer page, Integer limit, String sort, String order, String name) {
        //先分页
        PageHelper.startPage(page,limit);
        //创建一个排序的条件
        StorageExample storageExample = new StorageExample();
        storageExample.setOrderByClause(sort + " " + order);
        storageExample.createCriteria().andDeletedEqualTo(false).andNameLike("%" + name + "%");
        return getStorageListVo(storageExample);
    }

    @Override
    public StorageListVo queryStorageListByKey(Integer page, Integer limit, String sort, String order, String key) {
        //先分页
        PageHelper.startPage(page,limit);
        //创建一个排序的条件
        StorageExample storageExample = new StorageExample();
        storageExample.setOrderByClause(sort + " " + order);
        storageExample.createCriteria().andDeletedEqualTo(false).andKeyLike("%" + key + "%");
        return getStorageListVo(storageExample);
    }

    @Override
    public StorageListVo queryStorageListByKeyAndName(Integer page, Integer limit, String sort, String order, String key, String name) {
        //先分页
        PageHelper.startPage(page,limit);
        //创建一个排序的条件
        StorageExample storageExample = new StorageExample();
        storageExample.setOrderByClause(sort + " " + order);
        storageExample.createCriteria().andDeletedEqualTo(false).andKeyLike("%" + key + "%").andNameLike("%" + name + "%");
        return getStorageListVo(storageExample);
    }

    @Override
    public Storage createStorage(MultipartFile file) {
        String filename = file.getOriginalFilename();
        //key的后缀
        String substring = filename.substring(filename.lastIndexOf("."));

        Storage storage = new Storage();
        storage.setId(null);
        storage.setKey(UUID.randomUUID().toString() + substring);
        storage.setName(filename);
        storage.setType(file.getContentType());
        storage.setSize((int) file.getSize());
        storage.setUrl("http://localhost:8083/" + filename);
        storage.setAddTime(new Date());
        storage.setUpdateTime(new Date());
        storage.setDeleted(false);
        storageMapper.insert(storage);
        return storage;
    }

    @Override
    public Storage updateStorage(Storage storage) {
        Date date = new Date();
        storage.setUpdateTime(date);
        storageMapper.updateByPrimaryKey(storage);
        return storage;
    }

    @Override
    public void logicDeleteStorage(Storage storage) {
        storage.setDeleted(true);
        storageMapper.updateByPrimaryKey(storage);
    }



    public Storage createFile(MultipartFile file, String newFlieName) {
        /*
        ②封装数据，并插入数据库中
        private int id;
        private String key;     nq0t19sxu49ttknzhdch.png
        private String name;    面向对象.png
        private String type;    image/png
        private int size;       47357
        private String url;     http://182.92.235.201:8083/wx/storage/fetch/nq0t19sxu49ttknzhdch.png
        private String addTime;        2020-06-28 17:50:18
        private String updateTime;     2020-06-28 17:50:18
         */
        Storage storage = new Storage();
        storage.setId(null);
        storage.setKey(newFlieName);
        storage.setName(file.getOriginalFilename());
        storage.setType(file.getContentType());
        storage.setSize((int)file.getSize());
        storage.setUrl("http://localhost:8083/"+newFlieName);
        storage.setAddTime(new Date());
        storage.setUpdateTime(new Date());
        storage.setDeleted(false);

        storageMapper.insert(storage);
        return storage;
    }
}
