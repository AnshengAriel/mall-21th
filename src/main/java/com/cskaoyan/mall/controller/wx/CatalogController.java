package com.cskaoyan.mall.controller.wx;

import com.cskaoyan.mall.bean.BaseRespVo;
import com.cskaoyan.mall.bean.bean_of_hanyuandong.wx.CurrentCatalogVo;
import com.cskaoyan.mall.bean.bean_of_hanyuandong.wx.IndexCatalogVo;
import com.cskaoyan.mall.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("wx/catalog/")
public class CatalogController {

    @Autowired
    CatalogService catalogService;

    @RequestMapping("current")
    public BaseRespVo currentCatalog(Integer id) {
        CurrentCatalogVo catalogVo = catalogService.queryCategoryById(id);
        return BaseRespVo.ok(catalogVo);
    }

    @RequestMapping("index")
    public BaseRespVo IndexCatalog() {
        IndexCatalogVo catalogVo = catalogService.queryCategory();
        return BaseRespVo.ok(catalogVo);
    }
}
