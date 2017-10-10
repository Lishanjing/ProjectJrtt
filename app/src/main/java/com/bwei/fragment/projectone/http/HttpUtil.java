package com.bwei.fragment.projectone.http;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpUtil {


    //在activity中调用此方法，请求数据，并获取返回的数据；
    public void getDataFromServer(Context context, RequestBean bean, DataCallBack callBack) {
        MyHandler handler = new MyHandler(context, callBack);
        /***** 起子线程从网络开始获取数据******/
        MyTask task = new MyTask(handler, bean);
        //获取CPU数量
        int cpunum = Runtime.getRuntime().availableProcessors();
        //线程池实例化
        ExecutorService service = Executors.newScheduledThreadPool(cpunum + 1);
        //将子线程放入线程池执行；
        service.execute(task);
    }

    public abstract interface DataCallBack {
        public abstract void prosseData(String json);
    }

    //网络请求，开启子线程
    class MyTask extends Thread {
        private MyHandler handler;
        private RequestBean requestBean;

        public MyTask(MyHandler handler, RequestBean bean) {
            this.handler = handler;
            requestBean = bean;
        }

        @Override
        public void run() {
            super.run();
            try {
                //网络请求，HttpURLConnection
                URL url = new URL(requestBean.url);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                //判断是否是post请求；
                if (requestBean.method.equals("POST")) {
                    //设置请求方法；默认是get请求；
                    connection.setRequestMethod(requestBean.method);
                    //允许写入数据；
                    connection.setDoOutput(true);
                    //获取输出流，写入value；添加请求接口时需要追加的参数；
                    OutputStream os = connection.getOutputStream();
                    os.write(requestBean.value.getBytes());
                }
                //根据请求结果，对请求回来的数据进行处理；
                StringBuilder builder = new StringBuilder();
                //获取网络请求状态码；
                int code = connection.getResponseCode();
                if (code == HttpURLConnection.HTTP_OK) {//返回成功、
                    //请求结果从输入流里获取；
                    InputStream is = connection.getInputStream();
                    String str;
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                    while ((str = reader.readLine()) != null) {
                        builder.append(str);
                    }
                    Log.e("http-util:getdata", builder.toString());
                }
                //使用handler发送请求回来的数据到主线程；
                Message msg = Message.obtain();
                //请求结果放入object；如果请求成功，有数据；如果请求失败（状态码不为200，此时object为""）；
                msg.obj = builder.toString();
                //回传状态码，用于提醒用户；
                msg.what = code;
                handler.sendMessage(msg);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //更新UI回传数据；
    class MyHandler extends Handler {
        private Context context;//用于toast提示；
        private DataCallBack callBack;

        //如果没有toast提示，context可不传；
        public MyHandler(Context context, DataCallBack callBack) {
            this.context = context;
            this.callBack = callBack;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int what = msg.what;//获取网络连接状态码；
            if (what == 200) {//数据接口返回数据成功
                String result = (String) msg.obj;//result是从服务器端获取的json字符串；
                callBack.prosseData(result);//调用接口内需要实现的方法，方法内的代码都在此处执行；

            } else {
                Toast.makeText(context, "请求失败！", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
