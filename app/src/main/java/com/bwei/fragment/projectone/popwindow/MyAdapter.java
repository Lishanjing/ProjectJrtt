package com.bwei.fragment.projectone.popwindow;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bwei.fragment.projectone.R;

import java.util.List;

/**
 * Created by User on 2017/9/13.
 */

public class MyAdapter extends BaseAdapter{
    private List<String> list;
    private Context context;

    public MyAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        viewHolder holder=null;
        if (view==null){
            holder=new viewHolder();
            view=View.inflate(context, R.layout.item_pop,null);
            holder.tv= view.findViewById(R.id.tv);
            view.setTag(holder);
        }else {
            holder= (viewHolder) view.getTag();
        }
        holder.tv.setText(list.get(i));
        return view;
    }
    class viewHolder{
        TextView tv;
    }
}
