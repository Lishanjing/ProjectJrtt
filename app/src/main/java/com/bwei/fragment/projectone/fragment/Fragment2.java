package com.bwei.fragment.projectone.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwei.fragment.projectone.R;
import com.bwei.fragment.projectone.adapter.MyAdapter2;
import com.bwei.fragment.projectone.bean.Bean2;
import com.bwei.fragment.projectone.http.HttpUtil;
import com.bwei.fragment.projectone.http.RequestBean;
import com.bwei.fragment.projectone.xunitls.XListView;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by User on 2017/9/5.
 */

public class Fragment2 extends Fragment{
    private XListView listView;
    private MyAdapter2 adapter;
    private List<Bean2.DataBean> list;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=View.inflate(getActivity(), R.layout.fragment02,null);
        listView = (XListView) view.findViewById(R.id.fragment1_lv);
        listView.setPullRefreshEnable(true);
        listView.setPullLoadEnable(true);
        adapter = new MyAdapter2(getActivity());
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

        getData(true);
        return view;
    }
    private void getData(final boolean isAdd){
        String path="http://ic.snssdk.com/2/article/v25/stream/?category=news_hot&count=20&min_behot";
        new HttpUtil().getDataFromServer(getActivity(), new RequestBean(path), new HttpUtil.DataCallBack() {
            @Override
            public void prosseData(String json) {
                Gson gson = new Gson();
                Bean2 bean2 = gson.fromJson(json, Bean2.class);
                list = bean2.getData();
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
