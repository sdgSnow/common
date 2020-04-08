package com.sdg.commonlibrary.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * gson工具类
 * @author sdg
 * 2020/4/3
 */
public class GsonUtil {

    /**
     * 将bean转换成Json字符串
     * @param bean 待转换的对象
     * */
    public static String bean2json(Object bean) {
        return new Gson().toJson(bean);
    }

    /**
     * 将Json字符串转换成对象
     * @param json 待转换的json字符串
     * @param beanClass 转换对应的对象
     * */
    public static Object json2bject(String json, Class beanClass) {
        Gson gson = new Gson();
        Object res = gson.fromJson(json, beanClass);
        return res;
    }

    /**
     * 将json字符串转换成list
     * @param json 待转换的json字符串
     * @param clazz list所对应的泛型实体
     * */
    public static <T> List<T> json2List(String json, Class clazz) {
        Type type = new ParameterizedTypeImpl(clazz);
        List<T> list = new Gson().fromJson(json, type);
        return list;
    }

    private static class ParameterizedTypeImpl implements ParameterizedType {
        Class clazz;

        public ParameterizedTypeImpl(Class clz) {
            clazz = clz;
        }

        @Override
        public Type[] getActualTypeArguments() {
            return new Type[]{clazz};
        }

        @Override
        public Type getRawType() {
            return List.class;
        }

        @Override
        public Type getOwnerType() {
            return null;
        }
    }


}
