package com.ldrong.citydemo.city;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;

import com.apkfuns.logutils.LogUtils;
import com.ldrong.citydemo.R;
import com.ldrong.citydemo.conn.BaseActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author ldr
 *         created at 2016/10/28 16:00
 * @Description: 类的描述 -选择城市ac
 */
public class SelectCityActivity extends BaseActivity {
    public static final String City = "City";
    public static final String Doctor = "Doctor";
    public static final String Nurse = "Nurse";
    public static final String Consultation = "Consultation";
    private static final String MY_PWD = "aa";
    @BindView(R.id.lv_income)
    ExpandableListView expandableListView;

    private SelectCityActivity mContext;
    private List<String> list = new ArrayList<>();                              //第一次获得未打开时候的父类列表
    private ArrayList<String> parent = new ArrayList<>();                        //父类的key，就是头部显示的东西
    private HashMap<String, List<String>> map = new HashMap<String, List<String>>();     //存放所有数据的map，key -- 父类  value -- 对应里面的集合
    private SelectCityAdapter adapter;
    private List<String> mSelect = new ArrayList<>(); //被选择的城市
    //记录，是否被点击，打开过
    private List<String> mark = new ArrayList<String>();
    private String mType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_select);
        ButterKnife.bind(this);
        mContext = this;

        try {
            //查询所有省
            initView();
            setAdapter();
            setListener();
        } catch (Exception e) {
            LogUtils.e(TAG, "打印信息 >> : " + e);
        }
    }

    private void initView() {
        //父层与子层的关联
        //父层的key值 就是省名字
        parent = new ArrayList<String>();
        try {
            list = CityHelper.fetchAllProvinceNames();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //把父层的数据都作为key
        for (int i = 0; i < list.size(); i++) {
            parent.add(list.get(i));
            map.put(list.get(i), new ArrayList<String>());
        }

    }


    private void setAdapter() {

        adapter = new SelectCityAdapter(mContext, parent, map, mSelect, new SelectCityAdapter.OnCityClick() {
            @Override
            public void onCityInfo(String cityName) {
                LogUtils.e("当前选择 的城市是：" + cityName);
                showToastMsg(cityName + "");
            }
        });
        expandableListView.setAdapter(adapter);
        //去掉默认的左边的箭头
        expandableListView.setGroupIndicator(null);
        //打开其中一个，关闭其他
//        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
//
//            @Override
//            public void onGroupExpand(int groupPosition) {
//                for (int i = 0, count = expandableListView
//                        .getExpandableListAdapter().getGroupCount(); i < count; i++) {
//                    if (groupPosition != i) {// 关闭其他分组
//                        expandableListView.collapseGroup(i);
//                    }
//                }
//            }
//        });


    }

    private void setListener() {
        //设置父层点击事件
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parentview, View v, int groupPosition, long id) {

                boolean enable = parentview.isGroupExpanded(groupPosition);
                //Log.e(TAG, "是否打开: " + enable);
                if (!enable) {
                    try {
                        if (mark.contains("" + groupPosition)) {
                            //标记当前父层的position，如果已经点击过，就不处理；未点击过就调接口
                        } else {
                            mark.add("" + groupPosition);
                            //根据省名字，获得该省下的所有城市
                            List<String> mCity = CityHelper.getCitysByProvince(parent.get(groupPosition));
                            //当该省下的城市获取成功之后，要把对应的list，put进去关联起来
                            map.put(parent.get(groupPosition), mCity);
                            adapter.notifyDataSetChanged();
                        }
                    } catch (Exception e) {
                        Log.e(TAG, "onGroupClick: ", e);
                    }
                }
                return false;
            }
        });


    }


}
