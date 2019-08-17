package com.weimei.mylibrary;
/*
日期:2019/8/17
时间:22:34
项目名称:Aestheticism_Demo
开发者：赵学冲
*/

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkhttpUtils {
    private static volatile OkhttpUtils okhttpUtils;

    protected OkhttpUtils() {


    }

    public static OkhttpUtils OkhttpUtils() {
        if (okhttpUtils == null) {
          synchronized (OkhttpUtils.class){
              if (null==okhttpUtils){
                  okhttpUtils=new OkhttpUtils();
              }
          }
        }
        return  okhttpUtils;
    }


    //初始化Okhttp
    public void initOkhttp(String url, final OnOkhttpLisener onOkhttpLisener) {
        OkHttpClient client = new OkHttpClient();
        Request build = new Request.Builder().url(url).get().build();
        Call call = client.newCall(build);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                onOkhttpLisener.onError(e.getMessage());

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String s = response.body().string();
                onOkhttpLisener.onSucceed(s);
            }
        });



    }


    public interface OnOkhttpLisener{
        void onSucceed(String succeed);
        void onError(String ereor);


    }
}
