package com.cskaoyan.mall.bean.bean_of_liuxufeng;

import java.util.List;

public class GrouponRules_test {

    public static class DataBean_Need {
        private int total;
        private List<ItemsBean> items;
        public static class ItemsBean {
            private int id;
            private int goodsId;
            private String goodsName;
            private String picUrl;
            private int discount;
            private int discountMember;
            private String addTime;
            private String updateTime;
            private String expireTime;
            private boolean deleted;
        }
    }


    public static class MyDataBean {
        private int total;
        private List<ItemsBean> items;
        public static class ItemsBean {
            private int id;
            private int orderId;
            private int grouponId;
            private int rulesId;
            private int userId;
            private int creatorUserId;
            private String addTime;
            private String updateTime;
            private String shareUrl;
            private boolean payed;
            private boolean deleted;
        }
    }
}
