package com.cskaoyan.mall.utils;

public class CouponConverter {
    public static class Status{
        public static String code2value(Integer code){
            switch (code){
                case 0:
                    return "正常";
                case 1:
                    return "已过期";
                case 2:
                    return "已下架";
            }
            return "不可能出现的情况";
        }

        public static Integer value2code(String value){
            switch (value){
                case "正常":return 0;
                case "已过期":return 1;
                case "已下架":return 2;
            }
            return -1;
        }
    }

    public static class Type{
        public static String code2value(Integer code){
            switch (code){
                case 0:
                    return "通用领券";
                case 1:
                    return "注册赠券";
                case 2:
                    return "兑换码";
            }
            return "不可能出现的情况";
        }

        public static Integer value2code(String value){
            switch (value){
                case "通用领券":return 0;
                case "注册赠券":return 1;
                case "兑换码":return 2;
            }
            return -1;
        }
    }
}
