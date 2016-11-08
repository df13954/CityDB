package com.ldrong.citydemo.city;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.hhl.library.FlowTagLayout;
import com.hhl.library.OnTagSelectListener;
import com.ldrong.citydemo.R;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author ldr
 *         created at 2016/10/28 16:03
 * @Description: 类的描述 -城市选择的适配器
 */

public class SelectCityAdapter extends BaseExpandableListAdapter {

    private Context mContext;
    private List<String> parent;
    private HashMap<String, List<String>> map;
    private List<String> mSelect;
    private OnCityClick onCityClick;

    public SelectCityAdapter(Context context, List<String> parent, HashMap<String, List<String>> hashMap, List<String> select
            , OnCityClick onCityClick

    ) {
        this.mContext = context;
        this.parent = parent;
        this.map = hashMap;
        this.mSelect = select;
        this.onCityClick = onCityClick;
    }

    //设置父item组件
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parents) {
        ViewHolderP holder;
        if (convertView == null) {

            convertView = View.inflate(mContext, R.layout.item_pcitys, null);
            holder = new ViewHolderP(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolderP) convertView.getTag();
        }
        //父类
        holder.province.setText(parent.get(groupPosition));
        return convertView;
    }

    //获取当前父item的数据
    @Override
    public Object getGroup(int groupPosition) {
        return parent.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return parent.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }


    //得到子item需要关联的数据
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        String key = parent.get(groupPosition);
        return (map.get(key).get(childPosition));
    }

    //得到子item的ID
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    //设置子item的组件
    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parents) {
        String key = parent.get(groupPosition);
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_citys, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //城市
        TagAdapter<Object> tabAdapter = new TagAdapter<>(mContext, mSelect);
        holder.flowTab.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_SINGLE);
        holder.flowTab.setAdapter(tabAdapter);

        tabAdapter.onlyAddAll(map.get(key));
        holder.flowTab.setOnTagSelectListener(new OnTagSelectListener() {
            @Override
            public void onItemSelect(FlowTagLayout parent, List<Integer> selectedList) {
                if (selectedList != null && selectedList.size() > 0) {
                    StringBuilder sb = new StringBuilder();

                    for (int i : selectedList) {
                        //获得fl填充的所有tab的值
                        sb.append(parent.getAdapter().getItem(i));
                        //sb.append(":");
                    }
                    String ss = sb.toString();
                    LogUtils.e(sb.toString());
                    if (0 == mSelect.size()) {

                        mSelect.add(ss);
                        //把城市回调到ac中
                        onCityClick.onCityInfo(ss);
                    } else {
                        mSelect.clear();
                        mSelect.add(ss);
                        //把城市回调到ac中
                        onCityClick.onCityInfo(ss);
                        notifyDataSetChanged();
                    }
                } else {
                    //当取消了选择的是，就是是null了，同样回调“”
                    onCityClick.onCityInfo("");
                    mSelect.clear();
                    notifyDataSetChanged();
                }
            }
        });
        return convertView;
    }

    //获取当前父item下的子item的个数
    @Override
    public int getChildrenCount(int groupPosition) {
        //String key = parent.get(groupPosition);
        //int size = map.get(key).size();
        //这里可以把exp的子view的count 赋值==1
        return 1;
    }


    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    class ViewHolderP {
        @BindView(R.id.province)
        TextView province;

        ViewHolderP(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class ViewHolder {
        @BindView(R.id.flow_tab)
        FlowTagLayout flowTab;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    //点击之后把数据回调
    interface OnCityClick {
        void onCityInfo(String cityName);
    }


}
