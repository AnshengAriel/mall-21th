package com.cskaoyan.mall.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@MappedTypes(List.class)
public class RoleIdsTypeHandler extends BaseTypeHandler<List<Integer>> {


    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, List<Integer> list, JdbcType jdbcType) throws SQLException {
//        String s = parse(list);
//        preparedStatement.setString(i,s);
    }

    @Override
    public List<Integer> getNullableResult(ResultSet resultSet, String s) throws SQLException {
        //1.根据字段名从rs对象中获取字段值
        String columnValue = resultSet.getString(s);
        //3.转化逻辑
        return parse(columnValue);
    }

    @Override
    public List<Integer> getNullableResult(ResultSet resultSet, int i) throws SQLException {
        //1.根据字段名从rs对象中获取字段值
        String columnValue = resultSet.getString(i);

        //3.转化逻辑
        return parse(columnValue);
    }

    @Override
    public List<Integer> getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        //1.根据字段名从rs对象中获取字段值
        String columnValue = callableStatement.getString(i);

        //3.转化逻辑
        return parse(columnValue);
    }

    /**
     * 将字符串转化为List<Integer>
     * @param s
     * @return
     */
    private List<Integer> parse(String s) {
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
}
