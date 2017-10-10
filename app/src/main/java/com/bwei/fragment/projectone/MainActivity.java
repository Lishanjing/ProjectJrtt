package com.bwei.fragment.projectone;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;

import com.bwei.fragment.projectone.fragment.FragmentLeft;
import com.bwei.fragment.projectone.fragment.FragmentMain;
import com.bwei.fragment.projectone.view.TitleView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TabLayout mytab;
    private  TitleView titleView;
    private ViewPager viewPager;
    private DrawerLayout drawerLayout;
    String[] strName=new String[]{"推荐","热点","本地","视频","社会","娱乐","科技","汽车","体育","财经","军事","国际","段子","趣图","健康","美女"};
    private List<Fragment> first=new ArrayList<>();
    private MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setTitle();
        setTab();
        getSupportFragmentManager().beginTransaction().add(R.id.frag_left,new FragmentLeft()).commit();
    }
    private void setTab(){
        //设置tab文字
            for (int i=0;i<16;i++){
         mytab.addTab(mytab.newTab().setText(strName[i]));
        }
        for (int i=0;i<16;i++){
            first.add(new FragmentMain());
        }
        adapter = new MyAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        mytab.setupWithViewPager(viewPager);
    }
    class MyAdapter extends FragmentPagerAdapter{

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return first.get(position);
        }

        @Override
        public int getCount() {
            return first.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return strName[position];
        }
    }
    private void initView(){
        //初始化控件
        titleView= (TitleView) findViewById(R.id.mytitle);
        mytab= (TabLayout) findViewById(R.id.mytab);
        viewPager= (ViewPager) findViewById(R.id.vp);
        drawerLayout= (DrawerLayout) findViewById(R.id.activity_main);
    }
    private void setTitle(){
          titleView.setLeftListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  drawerLayout.openDrawer(Gravity.LEFT);
              }
          });
        titleView.setRightListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MainMessageActivity.class);
                startActivity(intent);
            }
        });
    }
}
