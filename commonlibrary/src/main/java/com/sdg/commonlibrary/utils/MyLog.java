package com.sdg.commonlibrary.utils;

import com.socks.library.KLog;

/**
 * log
 * @author sdg
 * 2020/4/3
 * 此封装便于后期如果想不使用klog，而且直接在这里修改
 */
public class MyLog {

    /**
     * d log
     * @param msg
     * */
    public static void d(String msg){
        KLog.d(msg);
    }

    /**
     * d log
     * @param msg
     * @param tag
     * */
    public static void d(String tag,String msg){
        KLog.d(tag,msg);
    }

    /**
     * i log
     * @param msg
     * */
    public static void i(String msg){
        KLog.i(msg);
    }

    /**
     * i log
     * @param msg
     * @param tag
     * */
    public static void i(String tag,String msg){
        KLog.i(tag,msg);
    }

    /**
     * w log
     * @param msg
     * */
    public static void w(String msg){
        KLog.w(msg);
    }

    /**
     * w log
     * @param msg
     * @param tag
     * */
    public static void w(String tag,String msg){
        KLog.w(tag,msg);
    }

    /**
     * e log
     * @param msg
     * */
    public static void e(String msg){
        KLog.e(msg);
    }

    /**
     * e log
     * @param msg
     * @param tag
     * */
    public static void e(String tag,String msg){
        KLog.e(tag,msg);
    }

    /**
     * a log
     * @param msg
     * */
    public static void a(String msg){
        KLog.a(msg);
    }

    /**
     * a log
     * @param msg
     * @param tag
     * */
    public static void a(String tag,String msg){
        KLog.a(tag,msg);
    }
}
