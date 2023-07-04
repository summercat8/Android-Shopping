package com.example.shopping4.utils;

/**
 * created by: xy
 * on: 2023/6/4
 * description:配置各个页面联网地址
 */
public class Constants {

//    public static String BASE_URL = "http://10.171.63.93:8080/xy";
    public static String BASE_URL = "http://192.168.152.1:8080/xy";//ipv4地址会发生改变，之前的失效了

    /**
     * 主页面的路径
     */
    public static String HOME_URL  = BASE_URL+"/json/HOME_URL.json";
    /**
     * 图片的基本路径
     */
    public static String BASE_URL_IMAGE  = BASE_URL+"/img";
}
