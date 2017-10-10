package com.bwei.fragment.projectone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bwei.fragment.projectone.db.MyManager;

import java.util.List;

public class MainLoginActivity extends AppCompatActivity {

    private MyManager manager;
    private EditText login_name;
    private EditText login_pwd;
    private Button login_btn;
    private Button login_btn1;
    List<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
        login_name = (EditText) findViewById(R.id.login_name);
        login_pwd = (EditText) findViewById(R.id.login_pwd);
        login_btn = (Button) findViewById(R.id.login_btn);
        login_btn1 = (Button) findViewById(R.id.login_btn1);
        manager = new MyManager(MainLoginActivity.this);
         login_btn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                String lname=login_name.getText().toString();
                 String lpwd=login_pwd.getText().toString();
                 list = manager.query();
                 if (lname.equals(list.get(0))&&lpwd.equals(list.get(1))){
                     Intent intent = new Intent(MainLoginActivity.this,MainActivity.class);
                     startActivity(intent);
                 }else {
                     Toast.makeText(MainLoginActivity.this,"用户名或密码错误,请重新输入",Toast.LENGTH_LONG).show();
                 }
             }
         });
        login_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainLoginActivity.this,MainZhuceActivity.class);
                startActivity(intent);
            }
        });
    }
}
