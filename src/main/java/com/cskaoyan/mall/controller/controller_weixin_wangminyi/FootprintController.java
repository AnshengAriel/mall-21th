package com.cskaoyan.mall.controller.controller_weixin_wangminyi;


import com.cskaoyan.mall.bean.BaseRespVo;
import com.cskaoyan.mall.bean.bean_of_wangminyi.BaseData;
import com.cskaoyan.mall.bean.bean_of_wangminyi.bean_weixin_wangminyi.DataForFootprintList;
import com.cskaoyan.mall.bean.bean_of_wangminyi.bean_weixin_wangminyi.DataForFootprintListVo;
import com.cskaoyan.mall.bean.bean_of_wangminyi.bean_weixin_wangminyi.FootprintForList;
import com.cskaoyan.mall.bean.bean_of_wangminyi.bean_weixin_wangminyi.Wangminyi_Footprint;
import com.cskaoyan.mall.mapper.mapper_weixin_wangminyi.Wangminyi_FootprintMapper;
import com.cskaoyan.mall.service.service_weixin_wangminyi.Wangminyi_FootprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("wx/footprint/")
public class FootprintController {

    @Autowired
    Wangminyi_FootprintService footprintService;

    @Autowired
    Wangminyi_FootprintMapper footprintMapper;

    @RequestMapping("list")
    public DataForFootprintListVo list(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        List<Wangminyi_Footprint> footprints = footprintService.queryAllFootprints(page, size);
        List<FootprintForList> footprintForLists = footprintService.footprintToFootprintForListVo(footprints);
        DataForFootprintList dataForFootprintList = new DataForFootprintList(footprintForLists, footprintForLists.size());
        DataForFootprintListVo dataForFootprintListVo = new DataForFootprintListVo(dataForFootprintList, "成功", 0);
        return dataForFootprintListVo;
    }

    @RequestMapping("delete")
    public BaseRespVo delete(@RequestBody String id) {
        //id：   {"id":6}
        String temp = id.substring(6, id.length() - 1);
        footprintService.deleteFootprint(Integer.valueOf(temp));
        return BaseRespVo.ok();
    }
}
