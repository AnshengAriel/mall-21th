package com.cskaoyan.mall.bean.bean_of_liuxufeng;

import java.util.List;

public class Groupon_test {

    public static class DataBean1 {
        private int total;
        private List<ItemsBean> items;

        public static class ItemsBean {
            /**
             * id : 2
             * goodsId : 1109008
             * goodsName : 云端沙发组合
             * picUrl : http://yanxuan.nosdn.127.net/c5be2604c0e4186a4e7079feeb742cee.png
             * discount : 50
             * discountMember : 5
             * addTime : 2018-11-08T05:42:44.000+00:00
             * updateTime : 2018-11-08T05:42:44.000+00:00
             * expireTime : 2019-12-30T16:00:00.000+00:00
             * deleted : false
             */
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

//团购活动需要返回的数据： ----
    public static class DataBean2 {
        private int total;
        private List<ItemsBean> items;

        public static class ItemsBean {
            private GrouponBean groupon;
            private GoodsBean goods;
            private RulesBean rules;
            private List<?> subGroupons;

            public static class GrouponBean {
                private int id;
                private int orderId;
                private int grouponId;
                private int rulesId;
                private int userId;
                private int creatorUserId;
                private String addTime;
                private String updateTime;
                private boolean payed;
                private boolean deleted;
            }

            public static class GoodsBean {
                private int id;
                private String goodsSn;
                private String name;
                private int categoryId;
                private int brandId;
                private String keywords;
                private String brief;
                private boolean isOnSale;
                private int sortOrder;
                private String picUrl;
                private String shareUrl;
                private boolean isNew;
                private boolean isHot;
                private String unit;
                private int counterPrice;
                private int retailPrice;
                private String addTime;
                private String updateTime;
                private boolean deleted;
                private String detail;
                private List<String> gallery;
            }

            public static class RulesBean {
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
    }
}

