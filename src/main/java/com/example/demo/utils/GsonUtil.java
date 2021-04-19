package com.example.demo.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author licong
 * @date 2021/4/20 00:02
 */
public abstract class GsonUtil {

    public static final String dateFormat = "yyyy-MM-dd HH:mm:ss";

    /**
     * 将object对象转成json格式字符串
     */
    public static String toJson(Object object) {
        return getGson().toJson(object);
    }

    public static <T> T toObject(String jsonString, Class<T> tClass) {
        return getGson().fromJson(jsonString, tClass);
    }


    public static Gson getGson(){
        return new GsonBuilder()
                .setDateFormat(dateFormat)
                .setPrettyPrinting()
                .create();
    }

}
