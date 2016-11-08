package com.ldrong.citydemo.city;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hhl.library.OnInitSelectedPosition;
import com.ldrong.citydemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HanHailong on 15/10/19.
 */
public class TagAdapter<T> extends BaseAdapter implements OnInitSelectedPosition {

    private final Context mContext;
    private final List<String> mDataList;

    private List<String> ms;

    public TagAdapter(Context context, List<String> ms) {
        this.mContext = context;
        this.ms = ms;
        mDataList = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.tag_item, null);

        TextView textView = (TextView) view.findViewById(R.id.tv_tag);
        String t = mDataList.get(position);

        if (t instanceof String) {
            textView.setText((String) t + "");
        }
        return view;
    }

    public void onlyAddAll(List<String> datas) {
        mDataList.addAll(datas);
        notifyDataSetChanged();
    }

    public void clearAndAddAll(List<String> datas) {
        mDataList.clear();
        onlyAddAll(datas);
    }

    /**
     * 如何设置默认选择
     *
     * @param position
     * @return
     */
    @Override
    public boolean isSelectedPosition(int position) {
        //例如用户选择了多个，应该是一个List，然后我们对比当前position的值，是否在集合中
        if (ms.contains(mDataList.get(position))) {
            return true;
        }
        return false;
    }
}
