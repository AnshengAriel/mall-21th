package com.cskaoyan.mall.utils;



import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TypeConverter {
    /**
     * List<Integer>  ——》  String
     * @param list
     * @return
     */
    public static String parse(List<Integer> list){
        StringBuilder s = new StringBuilder();
        s.append("[");
        for (Integer integer : list) {
            s.append(integer).append(",");
        }
        if (s.length() > 1){
            s.delete(s.length() - 1,s.length());
        }
        s.append("]");
        return s.toString();
    }

    /**
     * String  ——》 List<Integer>
     * @param s
     * @return
     */
    public static List<Integer> parse(String s) {
        if(s == null || s.length() == 0) {
            return null;
        }
        int length = s.length();
        if (length > 2) {
            String substring = s.substring(1, length-1);
            String[] split = substring.split(",");
            List<Integer> integers = new ArrayList<>();
            for (String s1 : split) {
                integers.add(Integer.parseInt(s1));
            }
            return integers;
        }
        return null;
    }

    public static String parseS(List<String> list) {
        StringBuilder sb = new StringBuilder();
        int size = list.size();
        if (size == 0) return "[]";
        sb.append("[\"");
        for (String s : list) {
            sb.append(s).append("\",");
        }
        sb.delete(sb.length() - 1,sb.length()).append("]");
        return sb.toString();
    }
}

