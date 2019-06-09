package com.bawei;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login("16619948760","123");
    }

    private void login(String s, String s1) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .build();
        // 通过表单请求体请求，请求方式为post
        RequestBody requestBody = new FormBody.Builder()
                .add("phone",s)
                .add("pwd",s1).build();
        Request request = new Request.Builder()
                .url("http://172.17.8.100/small/user/v1/login")
                .post(requestBody)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                System.out.println("result =="+string);
            }
        });
    }
}
