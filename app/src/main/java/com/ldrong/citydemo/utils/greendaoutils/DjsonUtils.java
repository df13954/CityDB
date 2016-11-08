package com.ldrong.citydemo.utils.greendaoutils;

import com.alibaba.fastjson.JSON;

import java.util.Map;

/**
 * denggm on 2015/10/19.
 * DHome
 */
public class DjsonUtils {

    public static String bean2Json(Object obj) {
        return JSON.toJSONString(obj);
    }

    public static <T> T json2Bean(String jsonStr, Class<T> objClass) {
        return JSON.parseObject(jsonStr, objClass);
    }

    public static Map json2Map(String jsonStr) {
        return json2Bean(jsonStr, Map.class);
    }

    public static Map bean2Map(Object obj) {
        String json = bean2Json(obj);
        return json2Map(json);
    }
}
