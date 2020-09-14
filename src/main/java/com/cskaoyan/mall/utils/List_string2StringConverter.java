package com.cskaoyan.mall.utils;



import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class List_string2StringConverter {

    public static String parse(List<String> list){
        String join = String.join(",", list);
        return join;
    }

    public static List parse(String s) {
        String str = s.substring(1,s.lastIndexOf("]"));
        List<String> lis = Arrays.asList(str.split(","));
        List list = new ArrayList<>();
        for (String string : lis) {
            if(string.indexOf("\"")!=-1)
            {string = string.substring(1,string.lastIndexOf("\""));}
            list.add(string);
        }
        return list;
    }

}

