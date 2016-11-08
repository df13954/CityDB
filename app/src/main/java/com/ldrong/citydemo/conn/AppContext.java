package com.ldrong.citydemo.conn;

import android.app.Application;

import com.apkfuns.logutils.LogLevel;
import com.apkfuns.logutils.LogUtils;
import com.ldrong.citydemo.db.DaoSession;
import com.ldrong.citydemo.utils.greendaoutils.DatabaseManagerImpl;
import com.ldrong.citydemo.utils.greendaoutils.LoaderDB;


/**
 * Created by ldr on 2016/10/12.
 */

public class AppContext extends Application {
    private static final String TAG = "AppContext";
    private static DatabaseManagerImpl databaseManager;

    @Override
    public void onCreate() {
        super.onCreate();
//        //初始化请求
//        OkHttpClient okHttpClient = new OkHttpClient.Builder()
////                .addInterceptor(new LoggerInterceptor("TAG"))
//                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
//                .readTimeout(10000L, TimeUnit.MILLISECONDS)
//                //其他配置
//                .build();
//
//        OkHttpUtils.initClient(okHttpClient);

        //日志初始化
        LogUtils.getLogConfig()
                .configAllowLog(true)
                .configTagPrefix("baseApp")
                .configShowBorders(true)
                .configFormatTag("%d{HH:mm:ss:SSS} %t %c{-5}")
                .configLevel(LogLevel.TYPE_INFO);
        //初始化数据库，打开数据库
        initGreenDao();
        //第一次写入数据库
        //        这个地方，可以做个标识，写入一次就够了
        new LoaderDB().loading(getApplicationContext());
    }

    /**
     * 初始化数据库，打开数据库
     */
    private void initGreenDao() {
        databaseManager = new DatabaseManagerImpl();
        databaseManager.startup(this);
    }

    /**
     * 提供外部使用
     *
     * @return DaoSession
     */
    public static DaoSession getGreenDaoSessino() {
        return databaseManager.getDaoSession();
    }
}
