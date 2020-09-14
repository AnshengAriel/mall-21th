package com.cskaoyan.mall.service.service_weixin_wangminyi;

import com.cskaoyan.mall.bean.bean_of_wangminyi.bean_weixin_wangminyi.FootprintForList;
import com.cskaoyan.mall.bean.bean_of_wangminyi.bean_weixin_wangminyi.Wangminyi_Footprint;

import java.util.List;

public interface Wangminyi_FootprintService {

    List<Wangminyi_Footprint> queryAllFootprints(Integer page, Integer size);

    List<FootprintForList> footprintToFootprintForListVo(List<Wangminyi_Footprint> footprints);

    void deleteFootprint(Integer footprintId);
}
