package com.bwei.fragment.projectone.fragment;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bwei.fragment.projectone.MainSmssdkActivity;
import com.bwei.fragment.projectone.MainVersionActivity;
import com.bwei.fragment.projectone.R;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by User on 2017/9/5.
 */

public class FragmentLeft extends Fragment{

    private View view;
    private ListView listView;
    private List<String> list=new ArrayList<>();
    private String[] name=new String[]{"好友动态","与我相关","我的头条","我的话题","我的文件","收藏","活动","商城","天气"};
    private MyAdapter adapter;
    private ImageView imageView;
    private ImageView img;
    private ImageView imageView1;
    private ImageView imageView2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.fragment_left,null);
        initview();
        initdata();
        adapter = new MyAdapter();
        listView.setAdapter(adapter);
        login();

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yejian();
            }
        });
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MainSmssdkActivity.class);
                startActivity(intent);
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MainVersionActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(getActivity()).onActivityResult(requestCode, resultCode, data);
    }
    public void login(){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UMShareAPI.get(getActivity()).getPlatformInfo(getActivity(), SHARE_MEDIA.QQ, new UMAuthListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {

                    }

                    @Override
                    public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {

                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media, int i) {

                    }
                });

            }
        });

    }
    private void yejian(){
        int mode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        if(mode == Configuration.UI_MODE_NIGHT_YES) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else if(mode == Configuration.UI_MODE_NIGHT_NO) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
     getActivity().recreate();
    }
    private void initview(){
        listView = (ListView) view.findViewById(R.id.lv);
        imageView = view.findViewById(R.id.mid);
        img = view.findViewById(R.id.buttom_mid);
        imageView1= view.findViewById(R.id.left);
        imageView2 = view.findViewById(R.id.buttom_left);
    }
    private void initdata(){
        for (int i=0;i<name.length;i++){
            list.add(name[i]);
        }
    }
    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
           view=View.inflate(getActivity(),R.layout.item_left,null);
          TextView textView=(TextView) view.findViewById(R.id.item);
            textView.setText(list.get(i));
            return view;
        }
    }

}
