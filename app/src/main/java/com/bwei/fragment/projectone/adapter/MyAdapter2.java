package com.bwei.fragment.projectone.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwei.fragment.projectone.R;
import com.bwei.fragment.projectone.bean.Bean2;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2017/9/7.
 */

public class MyAdapter2 extends BaseAdapter{
     int a=0;
    int b=1;
    int c=2;
    private Context context;
    DisplayImageOptions options;
    ImageLoader loader;
    List<Bean2.DataBean> list=new ArrayList<>();

    public MyAdapter2(Context context) {
        this.context = context;
        ImageLoaderConfiguration configuration=ImageLoaderConfiguration.createDefault(context);
        ImageLoader.getInstance().init(configuration);
        loader=ImageLoader.getInstance();
        options=new DisplayImageOptions.Builder().cacheOnDisk(true)
                .cacheInMemory(true)
                .build();
    }
public void addData(List<Bean2.DataBean> list){
    this.list.addAll(list);
    notifyDataSetChanged();
}
    public void updateData(List<Bean2.DataBean> list){
        this.list.clear();
        addData(list);
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        if(list!=null && list.size()>0){
            list.get(i);
        }
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemViewType(int position) {
        if (position==0){
            return 0;
        }else if (list.get(position).getLarge_image_list()!=null){
            return 1;
        }else
            return 2;


    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        int type=getItemViewType(i);
        ViewHolder holder=null;
        ViewHolder1 holder1=null;
        ViewHolder2 holder2=null;
        if (view==null){
            switch (type){
                case 0:
                    holder=new ViewHolder();
                    view=View.inflate(context, R.layout.fragment1_item0,null);
                    holder.fragment2_item0_title=(TextView) view.findViewById(R.id.fragment2_item0_title);
                    holder.fragment2_item0_source=(TextView)view.findViewById(R.id.fragment2_item0_source);
                    view.setTag(holder);
                    break;
                case 1:
                    holder1=new ViewHolder1();
                    view=View.inflate(context, R.layout.fragment1_item1,null);
                    holder1.fragment2_item1_title=(TextView) view.findViewById(R.id.fragment2_item1_title);
                    holder1.fragment2_item1_source=(TextView)view.findViewById(R.id.fragment2_item1_source);
                    holder1.fragment2_item1_img=(ImageView)view.findViewById(R.id.fragment2_item1_img);
                    view.setTag(holder1);
                    break;
                case 2:
                    holder2=new ViewHolder2();
                    view=View.inflate(context, R.layout.fragment1_item2,null);
                    holder2.fragment2_item2_source=(TextView)view.findViewById(R.id.fragment2_item2_source);
                    holder2.fragment2_item2_img1=(ImageView)view.findViewById(R.id.fragment2_item2_img1);
                    holder2.fragment2_item2_img2=(ImageView)view.findViewById(R.id.fragment2_item2_img2);
                    holder2.fragment2_item2_img3=(ImageView)view.findViewById(R.id.fragment2_item2_img3);
                    view.setTag(holder2);
                    break;
                default:
                    break;
            }
        }else {
            switch (type){
                case 0:
                    holder= (ViewHolder) view.getTag();
                    break;
                case 1:
                    holder1= (ViewHolder1) view.getTag();
                    break;
                case 2:
                    holder2= (ViewHolder2) view.getTag();
                    break;
                default:
                    break;
            }
        }
        String title=list.get(i).getTitle();
        String source=list.get(i).getSource();
        switch (type){
            case 0:
                holder.fragment2_item0_title.setText(title);
                holder.fragment2_item0_source.setText(source);
                break;
            case 1:
                holder1.fragment2_item1_title.setText(title);
                holder1.fragment2_item1_source.setText(source);
        //      loader.displayImage(list.get(i).getLarge_image_list().get(0).getUrl(),holder1.fragment2_item1_img,options);
                break;
            case 2:
                holder2.fragment2_item2_source.setText(source);
                loader.displayImage(list.get(i).getLarge_image_list().get(i).getUrl_list().get(0).getUrl(),holder2.fragment2_item2_img1,options);
                loader.displayImage(list.get(i).getLarge_image_list().get(i).getUrl_list().get(1).getUrl(),holder2.fragment2_item2_img1,options);
                loader.displayImage(list.get(i).getLarge_image_list().get(i).getUrl_list().get(2).getUrl(),holder2.fragment2_item2_img1,options);
                break;
            default:
                break;
        }
        return view;
    }
    class ViewHolder{
         TextView fragment2_item0_title,fragment2_item0_source;
    }
    class ViewHolder1{
        TextView fragment2_item1_title,fragment2_item1_source;
        ImageView fragment2_item1_img;
    }
    class ViewHolder2{
        TextView fragment2_item2_source;
        ImageView fragment2_item2_img1,fragment2_item2_img2,fragment2_item2_img3;
    }
}
