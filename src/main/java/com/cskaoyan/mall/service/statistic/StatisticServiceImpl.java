package com.cskaoyan.mall.service.statistic;

import com.cskaoyan.mall.bean.statistic.GoodBean;
import com.cskaoyan.mall.bean.statistic.OrderBean;
import com.cskaoyan.mall.bean.statistic.UserBean;
import com.cskaoyan.mall.mapper.statistic.StatisticMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StatisticServiceImpl implements StatisticService {

    @Autowired
    StatisticMapper statisticMapper;

    @Override
    public List<UserBean> queryUserStatistic() {
        return statisticMapper.selectUserStatistic();
    }

    @Override
    public List<OrderBean> queryOrderStatistic() {
        return statisticMapper.selectOrderStatistic();
    }

    @Override
    public List<GoodBean> queryGoodStatistic() {
        return statisticMapper.selectGoodStatistic();
    }
}
