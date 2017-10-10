package com.bwei.fragment.projectone.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bwei.fragment.projectone.R;

/**
 * Created by User on 2017/9/5.
 */

public class TitleView extends LinearLayout{

    private ImageView img1;
    private ImageView img2;
    private ImageView img3;

    public TitleView(Context context) {
        super(context);
        initView(context);
    }

    public TitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public TitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public void setLeftListener(OnClickListener listener){
        img1.setOnClickListener(listener);
    }
    public void setRightListener(OnClickListener listener){
        img3.setOnClickListener(listener);
    }
  public void initView(Context context){
      LayoutInflater.from(context).inflate(R.layout.title,this);
      img1 = (ImageView) findViewById(R.id.title_left);
      img2 = (ImageView) findViewById(R.id.title_center);
      img3 = (ImageView) findViewById(R.id.title_right);
  }
}
