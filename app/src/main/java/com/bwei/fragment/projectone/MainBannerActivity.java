package com.bwei.fragment.projectone;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import static com.bwei.fragment.projectone.R.id.btn;

public class MainBannerActivity extends AppCompatActivity {
    SharedPreferences sp;
    private ViewPager viewPager;
    private Button button;
    private List<View> list=new ArrayList<>();
    private ImageView my_img;
    int[] arr=new int[]{R.drawable.jj,R.drawable.jr,R.drawable.jt};
    int count=3;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            count--;
            if (count==0){
                Intent intent = new Intent(MainBannerActivity.this,MainLoginActivity.class);
                startActivity(intent);
            }
            sendEmptyMessageDelayed(0,1000);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        viewPager = (ViewPager) findViewById(R.id.vp);
        button = (Button) findViewById(btn);
        my_img= (ImageView) findViewById(R.id.my_Img);
        sp=getSharedPreferences("jrtt",MODE_PRIVATE);
        boolean isFirst = sp.getBoolean("isFirst",false);
        if (isFirst){
            my_img.setVisibility(View.VISIBLE);
            viewPager.setVisibility(View.GONE);
            button.setVisibility(View.VISIBLE);
            my_img.setImageResource(R.drawable.jrt);
            handler.sendEmptyMessage(0);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    handler.removeMessages(0);
                    Intent intent = new Intent(MainBannerActivity.this,MainLoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        }else {
            initData();
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
              container.addView(list.get(position));
                return list.get(position);
            }
        });
     viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
         @Override
         public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
             if (position==2){
                 Intent intent = new Intent(MainBannerActivity.this,MainLoginActivity.class);
                 startActivity(intent);
                 sp.edit().putBoolean("isFirst",true).commit();
             }
         }

         @Override
         public void onPageSelected(int position) {

         }

         @Override
         public void onPageScrollStateChanged(int state) {

         }
     });
        }
    }

    private void initData() {
        for (int i=0;i<arr.length;i++){
            ImageView view = new ImageView(MainBannerActivity.this);
            view.setImageResource(arr[i]);
            list.add(view);
        }
    }

}
