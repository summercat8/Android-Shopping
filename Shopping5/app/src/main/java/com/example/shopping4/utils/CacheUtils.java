package com.example.shopping4.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * created by: xy
 * on: 2023/6/4
 * description:缓存工具类
 */
public class CacheUtils {

    /**
     * 得到保持的String类型的数据
     * @param context
     * @param key
     * @return
     */
    public static String getString(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences("xy",Context.MODE_PRIVATE);
        return sp.getString(key,"");
    }


    /**
     * 保持String类型数据
     * @param context 上下文
     * @param key
     * @param value 保持的值
     */
    public static void saveString(Context context, String key,String value) {
        //SharedPreferences 是 Android 提供的一种轻量级存储方式，可用于保存和读取应用程序的配置数据。
        SharedPreferences sp = context.getSharedPreferences("xy",Context.MODE_PRIVATE);
        sp.edit().putString(key,value).commit();
    }
}
