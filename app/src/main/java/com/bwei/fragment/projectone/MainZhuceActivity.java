package com.bwei.fragment.projectone;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bwei.fragment.projectone.db.MyManager;

public class MainZhuceActivity extends AppCompatActivity {

    private EditText zc_name;
    private EditText zc_pwd;
    private Button button;
    private MyManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_zhuce);
        zc_name = (EditText) findViewById(R.id.zc_name);
        zc_pwd = (EditText) findViewById(R.id.zc_pwd);
        button = (Button) findViewById(R.id.zc_btn);
        manager = new MyManager(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=zc_name.getText().toString();
                String pwd=zc_pwd.getText().toString();
                manager.insert(name,pwd);
                Toast.makeText(MainZhuceActivity.this,"注册成功",Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}
