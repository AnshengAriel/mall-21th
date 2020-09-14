package com.cskaoyan.mall.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class BeanUtils {
    /**
     * 只取resultList中对应于bean中成员变量名的keyName，keyValue的值，封装成bean
     * @param resultList    包含keyName,keyValue成员变量名的实例
     * @param beanTypeClass 其中的成员变量作key，到list中去取值
     * @param <T>           返回值bean的类型
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */
    public  static  <T>  T toBeanByFieldNameOnKeyAndValue(List resultList, Class<T> beanTypeClass) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        if (resultList == null) {
            return null;
        }

        // 利用反射封装数据
        Object bean = beanTypeClass.newInstance();
        Field[] fields = beanTypeClass.getDeclaredFields();   // 反射，获取javabean中声明的成员变量
        for (Field field : fields) {
            String fieldName = field.getName();     // 获取成员变量名
            String setMethodName = "set" + getFirstCapital(fieldName);    // 获得set方法名
            Method setMethod = beanTypeClass.getMethod(setMethodName, String.class);
            for (Object result : resultList) {
                Class<?> aClass = result.getClass();
                Method getKeyNameMethod = aClass.getMethod("getKeyName");
                if (fieldName.equals(getKeyNameMethod.invoke(result, null))) {      // 找到对应的keyName
                    Method getKeyValueMethod = aClass.getMethod("getKeyValue",null);
                    setMethod.invoke(bean, getKeyValueMethod.invoke(result, null));  // 反射，调用方法封装数据
                }
            }
        }

        return (T) bean;
    }

    /**
     * 用了之后发现，没什么用= =
     * 至少在封装数据这个过程中，效率不高，还不如去写sql语句
     * 不建议使用，可能有问题
     * 两个bean,当一个bean的field是另一个的子集时，可使用此方法互相赋值
     *
     * 注意，赋值是过程仅是传递引用，不是复制。
     * @param resultList
     * @param beanTypeClass
     * @param <T>
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */
    public static <T> List<T> toBeanByFiledName(List resultList, Class<T> beanTypeClass) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        if (resultList.size() == 0 || resultList.get(0) == null) {
            return null;
        }

        List<T> beans = new ArrayList<>();

        // 利用反射封装数据
        Class<?> aClass = resultList.get(0).getClass();
        Field[] fs1 = aClass.getDeclaredFields();

        Field[] fields = beanTypeClass.getDeclaredFields();

        for (Object result : resultList) {
            Object bean = beanTypeClass.newInstance();
            for (Field field : fields) {
                String fieldName =  field.getName();    // 获取成员变量名
                String setMethodName = "set" + getFirstCapital(fieldName);    // 获得set方法名
                String getMethodName = "get" + getFirstCapital(fieldName);    // 获得get方法名
                Method setMethod = beanTypeClass.getMethod(setMethodName,field.getType());
                for (Field field1 : fs1) {
                    if (field1.getName() == fieldName) {      // 找到相同的fieldName
                        Method getMethod = null;
                        try {
                            getMethod = aClass.getMethod(getMethodName,null);
                        } catch (NoSuchMethodException e) {
                            // e.printStackTrace();// 不处理，因为没对应字段自然没有对应的方法
                        } catch (SecurityException e) {
                            e.printStackTrace();
                        }
                        setMethod.invoke(bean, getMethod.invoke(result, null));  // 反射，调用方法封装数据
                        continue;
                    }
                }
            }
            beans.add((T)bean); // 添加到list
        }

        return beans;
    }

    /**
     * 重写方法
     * @param result
     * @param beanTypeClass
     * @param <T>
     * @return  必定是该类型的对象，而不是null
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */
    public static <T> T toBeanByFiledName(Object result, Class<T> beanTypeClass) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {


        Object bean = beanTypeClass.newInstance();

        if (result == null) {
            return (T)bean;
        }

        // 利用反射封装数据
        Class<?> aClass = result.getClass();
        Field[] fs1 = aClass.getDeclaredFields();

        Field[] fields = beanTypeClass.getDeclaredFields();



        for (Field field : fields) {
            String fieldName =  field.getName();    // 获取成员变量名
            String setMethodName = "set" + getFirstCapital(fieldName);    // 获得set方法名
            String getMethodName = "get" + getFirstCapital(fieldName);    // 获得get方法名
            Method setMethod = beanTypeClass.getMethod(setMethodName,field.getType());
            for (Field field1 : fs1) {
                if (field1.getName() == fieldName) {      // 找到相同的fieldName
                    Method getMethod = null;
                    try {
                        getMethod = aClass.getMethod(getMethodName,null);
                    } catch (NoSuchMethodException e) {
                        // e.printStackTrace();// 不处理，因为没对应字段自然没有对应的方法
                    } catch (SecurityException e) {
                        e.printStackTrace();
                    }
                    setMethod.invoke(bean, getMethod.invoke(result, null));  // 反射，调用方法封装数据
                    continue;
                }
            }
        }
        return (T) bean;
    }

    /**
     * 首字母大写
     * @param fieldName
     * @return
     */
    private static String getFirstCapital(String fieldName) {
        char[] chars = fieldName.toCharArray();
        if (chars[0] > 'a') {
            chars[0] -= 32;
        }
        return new String(chars);
    }
}
