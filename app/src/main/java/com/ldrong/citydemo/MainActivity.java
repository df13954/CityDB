package com.ldrong.citydemo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.ldrong.citydemo.city.SelectCityActivity;
import com.ldrong.citydemo.conn.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.btn_city)
    Button btnCity;
    @BindView(R.id.activity_main)
    RelativeLayout activityMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        try {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        new DBHelper(mContext).createDataBase();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }).start();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @OnClick(R.id.btn_city)
    public void onClick() {
        Intent intent = new Intent(mContext, SelectCityActivity.class);
        startActivity(intent);
    }
}
