package com.bwei.fragment.projectone.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.bwei.fragment.projectone.MainWebActivity;
import com.bwei.fragment.projectone.R;
import com.bwei.fragment.projectone.adapter.MyAdapter;
import com.bwei.fragment.projectone.bean.Bean;
import com.bwei.fragment.projectone.http.HttpUtil;
import com.bwei.fragment.projectone.http.RequestBean;
import com.bwei.fragment.projectone.xunitls.XListView;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by User on 2017/9/5.
 */

public class FragmentMain extends Fragment{

    private XListView listView;
    private MyAdapter adapter;
    private List<Bean.DataBean> list;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=View.inflate(getActivity(), R.layout.fragment_main,null);
        listView = (XListView) view.findViewById(R.id.xlv);
        listView.setPullRefreshEnable(true);
        listView.setPullLoadEnable(true);
        adapter = new MyAdapter(getActivity());
        listView.setAdapter(adapter);
        listView.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                  getData(false);
            }

            @Override
            public void onLoadMore() {
               getData(true);
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), MainWebActivity.class);
                Bean.DataBean item= (Bean.DataBean) adapter.getItem(i);
                intent.putExtra("url",item.getUrl());
                startActivity(intent);
            }
        });
          getData(true);
        return view;
    }
   private void getData(final boolean isAdd){
       String path="http://ic.snssdk.com/2/article/v25/stream/?count=20&min_behot_time=1455521444&bd_city=%E5%8C%97%E4%BA%AC%E5%B8%82";
       new HttpUtil().getDataFromServer(getActivity(), new RequestBean(path), new HttpUtil.DataCallBack() {
           @Override
           public void prosseData(String json) {
               Gson gson = new Gson();
               Bean bean = gson.fromJson(json, Bean.class);
              list = bean.getData();
               if (isAdd){
                   adapter.addData(list);
               }else {
                   adapter.updateData(list);
               }
               listView.stopRefresh();
               listView.stopLoadMore();
           }
       });
   }


}
