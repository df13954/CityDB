package com.ldrong.citydemo.utils.greendaoutils;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.apkfuns.logutils.LogUtils;
import com.ldrong.citydemo.R;
import com.ldrong.citydemo.conn.AppContext;
import com.ldrong.citydemo.db.City;
import com.ldrong.citydemo.db.DaoSession;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * 加载本地数据库
 * Created by dlr on 2016/8/26.
 */
public class LoaderDB {

    private static final String TAG = "LoaderDB";

    public void loading(Context mContext) {
        DaoSession daoSession = AppContext.getGreenDaoSessino();
        //清空一次数据
        daoSession.getCityDao().deleteAll();
        String city = mContext.getResources().getString(R.string.db_city);
        try {
            //城市2883
            String listout = getArrayList(city);
            List<City> listCity = JSON.parseArray(listout, City.class);
            daoSession.getCityDao().insertInTx((City[]) listCity.toArray(new City[listCity.size()]));
            LogUtils.e(TAG, "2838城市 写入完成 个数：" + listCity.size());
        } catch (Exception e) {
            LogUtils.e(TAG, "解析：" + e);
        }
    }

    /**
     * 把数组取出来
     *
     * @param jsonStr
     * @return
     * @throws JSONException
     */

    public static String getArrayList(String jsonStr) throws JSONException {
        JSONObject jsonObject = null;
        jsonObject = new JSONObject(jsonStr);
        String str = jsonObject.getString("ReturnInfo");
        JSONObject obj = new JSONObject(str);
        return obj.getString("ListOut");
    }
}
