package com.cskaoyan.mall.bean.bean_of_liuxufeng;

import com.cskaoyan.mall.utils.List_string2StringConverter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class CartListVo {
        /**
         * cartTotal : {"goodsCount":5,"checkedGoodsCount":4,"goodsAmount":492,"checkedGoodsAmount":143}
         * cartList : [{"id":109,"userId":1,"goodsId":1113010,"goodsSn":"1113010","goodsName":"男式丝滑莫代尔平角内裤","productId":156,"price":59,"number":1,"specifications":["标准"],"checked":true,"picUrl":"http://yanxuan.nosdn.127.net/2d0920b51331bb1636330ad8e07d1b97.png","addTime":"2020-06-30 19:58:50","updateTime":"2020-06-30 19:58:50","deleted":false},{"id":110,"userId":1,"goodsId":1045000,"goodsSn":"1045000","goodsName":"绿茶蛋黄酥 200克/4枚入","productId":58,"price":28,"number":3,"specifications":["标准"],"checked":true,"picUrl":"http://yanxuan.nosdn.127.net/b2adc3fd9b84a289a1be03e8ee400e61.png","addTime":"2020-06-30 19:59:19","updateTime":"2020-06-30 19:59:19","deleted":false},{"id":111,"userId":1,"goodsId":1085019,"goodsSn":"1085019","goodsName":"20寸 纯PC\u201c铝框\u201d（非全铝）登机箱","productId":102,"price":349,"number":1,"specifications":["标准"],"checked":false,"picUrl":"http://yanxuan.nosdn.127.net/65c955a7a98e84d44ca30bb88a591eac.png","addTime":"2020-06-30 19:59:49","updateTime":"2020-06-30 20:00:00","deleted":false}]
         */

        private CartTotalBean cartTotal;
        private List<CartListBean> cartList;

        public CartTotalBean getCartTotal() {
            return cartTotal;
        }

        public void setCartTotal(CartTotalBean cartTotal) {
            this.cartTotal = cartTotal;
        }

        public List<CartListBean> getCartList() {
            return cartList;
        }

        public void setCartList(List<CartListBean> cartList) {
            this.cartList = cartList;
        }

        public static class CartTotalBean {
            /**
             * goodsCount : 5
             * checkedGoodsCount : 4
             * goodsAmount : 492
             * checkedGoodsAmount : 143
             */

            private int goodsCount;         //计算goods 的数量
            private int checkedGoodsCount;  // 计算checked 的数量

            private int goodsAmount = 9;    //暂时不知道怎么实现
            private int checkedGoodsAmount = 8;  //暂时不知道是什么鬼！

            public int getGoodsCount() {
                return goodsCount;
            }

            public void setGoodsCount(int goodsCount) {
                this.goodsCount = goodsCount;
            }

            public int getCheckedGoodsCount() {
                return checkedGoodsCount;
            }

            public void setCheckedGoodsCount(int checkedGoodsCount) {
                this.checkedGoodsCount = checkedGoodsCount;
            }

            public int getGoodsAmount() {
                return goodsAmount;
            }

            public void setGoodsAmount(int goodsAmount) {
                this.goodsAmount = goodsAmount;
            }

            public int getCheckedGoodsAmount() {
                return checkedGoodsAmount;
            }

            public void setCheckedGoodsAmount(int checkedGoodsAmount) {
                this.checkedGoodsAmount = checkedGoodsAmount;
            }
        }

       
}
