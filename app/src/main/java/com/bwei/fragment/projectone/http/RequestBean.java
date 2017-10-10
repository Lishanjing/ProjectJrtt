package com.bwei.fragment.projectone.http;



public class RequestBean {
    //请求网络接口需要用到的参数；
    public String url="";
    public String value="";
    public String method="GET";
//GET请求
    public RequestBean(String url) {
        this.url = url;
    }
//post请求
    public RequestBean(String url, String value, String method) {
        this.url = url;
        this.value = value;
        this.method = method;
    }
}
