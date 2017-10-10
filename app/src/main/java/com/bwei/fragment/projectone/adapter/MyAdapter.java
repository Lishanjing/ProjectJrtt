package com.bwei.fragment.projectone.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwei.fragment.projectone.R;
import com.bwei.fragment.projectone.bean.Bean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2017/9/7.
 */

public class MyAdapter extends BaseAdapter{
   int a=0;
    int b=1;
    int c=2;
    private Context context;
    DisplayImageOptions options;
    ImageLoader loader;
    List<Bean.DataBean> list=new ArrayList<>();

    public MyAdapter(Context context) {
        this.context = context;
        ImageLoaderConfiguration configuration=ImageLoaderConfiguration.createDefault(context);
        ImageLoader.getInstance().init(configuration);
        loader=ImageLoader.getInstance();
        options=new DisplayImageOptions.Builder().cacheOnDisk(true)
                .cacheInMemory(true)
                .build();
    }
public void addData(List<Bean.DataBean> list){
    this.list.addAll(list);
    notifyDataSetChanged();
}
    public void updateData(List<Bean.DataBean> list){
        this.list.clear();
        addData(list);
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
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (position==0){
            return 0;
        }else if (list.get(position).getLarge_image_list()!=null){
            return 1;
        }else if (list.get(position).getImage_list()!=null){
            return 2;
        }
        return 0;
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
                    view=View.inflate(context, R.layout.main_item0,null);
                    holder.main_item0_title=(TextView) view.findViewById(R.id.main_item0_title);
                    holder.main_item0_source=(TextView)view.findViewById(R.id.main_item0_source);
                    view.setTag(holder);
                    break;
                case 1:
                    holder1=new ViewHolder1();
                    view=View.inflate(context, R.layout.main_item1,null);
                    holder1.main_item1_title=(TextView) view.findViewById(R.id.main_item1_title);
                    holder1.main_item1_source=(TextView)view.findViewById(R.id.main_item1_source);
                    holder1.main_item1_img=(ImageView)view.findViewById(R.id.main_item1_img);
                    view.setTag(holder1);
                    break;
                case 2:
                    holder2=new ViewHolder2();
                    view=View.inflate(context, R.layout.main_item2,null);
                    holder2.main_item2_source=(TextView)view.findViewById(R.id.main_item2_source);
                    holder2.main_item2_img1=(ImageView)view.findViewById(R.id.main_item2_img1);
                    holder2.main_item2_img2=(ImageView)view.findViewById(R.id.main_item2_img2);
                    holder2.main_item2_img3=(ImageView)view.findViewById(R.id.main_item2_img3);
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
                holder.main_item0_title.setText(title);
                holder.main_item0_source.setText(source);
                break;
            case 1:
                holder1.main_item1_title.setText(title);
                holder1.main_item1_source.setText(source);
               loader.displayImage(list.get(i).getLarge_image_list().get(0).getUrl(),holder1.main_item1_img,options);
                break;
            case 2:
                holder2.main_item2_source.setText(source);
               loader.displayImage(list.get(i).getImage_list().get(0).getUrl(),holder2.main_item2_img1,options);
                loader.displayImage(list.get(i).getImage_list().get(1).getUrl(),holder2.main_item2_img2,options);
              loader.displayImage(list.get(i).getImage_list().get(2).getUrl(),holder2.main_item2_img3,options);
                break;
            default:
                break;
        }
        return view;
    }
    class ViewHolder{
         TextView main_item0_title,main_item0_source;
    }
    class ViewHolder1{
        TextView main_item1_title,main_item1_source;
        ImageView main_item1_img;
    }
    class ViewHolder2{
        TextView main_item2_source;
        ImageView main_item2_img1,main_item2_img2,main_item2_img3;
    }
}
