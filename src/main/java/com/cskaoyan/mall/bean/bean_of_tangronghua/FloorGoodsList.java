package com.cskaoyan.mall.bean.bean_of_tangronghua;

import java.util.List;

public class FloorGoodsList {
    /**
     * name : 餐厨
     * goodsList : [{"id":1181065,"name":"qtest","brief":"qtest","picUrl":"http://182.92.235.201:8083/wx/storage/fetch/tj23qjcrbgfb17ttt1zl.jpeg","isNew":true,"isHot":true,"counterPrice":200,"retailPrice":180},{"id":1181053,"name":"宠物玩具","brief":"儿童玩具","picUrl":"http://182.92.235.201:8083/wx/storage/fetch/hjb0ms3vv3t1of87ttvr.jpg","isNew":true,"isHot":false,"counterPrice":480,"retailPrice":420},{"id":1110016,"name":"天然硅胶宠物除毛按摩刷","brief":"顺滑平面，猫狗通用，去除死毛","picUrl":"http://yanxuan.nosdn.127.net/3bd73b7279a83d1cbb50c0e45778e6d6.png","isNew":true,"isHot":false,"counterPrice":59,"retailPrice":39},{"id":1073008,"name":"铸铁珐琅牛排煎锅","brief":"沥油隔水，煎出外焦里嫩","picUrl":"http://yanxuan.nosdn.127.net/619e46411ccd62e5c0f16692ee1a85a0.png","isNew":false,"isHot":false,"counterPrice":169,"retailPrice":149}]
     * id : 1005001
     */

    private String name;
    private int id;
    private List<GoodsListBean> goodsList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<GoodsListBean> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<GoodsListBean> goodsList) {
        this.goodsList = goodsList;
    }

    public static class GoodsListBean {
        /**
         * id : 1181065
         * name : qtest
         * brief : qtest
         * picUrl : http://182.92.235.201:8083/wx/storage/fetch/tj23qjcrbgfb17ttt1zl.jpeg
         * isNew : true
         * isHot : true
         * counterPrice : 200
         * retailPrice : 180
         */

        private int id;
        private String name;
        private String brief;
        private String picUrl;
        private boolean isNew;
        private boolean isHot;
        private int counterPrice;
        private int retailPrice;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getBrief() {
            return brief;
        }

        public void setBrief(String brief) {
            this.brief = brief;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public boolean isIsNew() {
            return isNew;
        }

        public void setIsNew(boolean isNew) {
            this.isNew = isNew;
        }

        public boolean isIsHot() {
            return isHot;
        }

        public void setIsHot(boolean isHot) {
            this.isHot = isHot;
        }

        public int getCounterPrice() {
            return counterPrice;
        }

        public void setCounterPrice(int counterPrice) {
            this.counterPrice = counterPrice;
        }

        public int getRetailPrice() {
            return retailPrice;
        }

        public void setRetailPrice(int retailPrice) {
            this.retailPrice = retailPrice;
        }
    }
}