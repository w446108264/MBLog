package com.mblog.simple;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.OnClick;
import sj.mblog.L;


public class MainActivity extends AppCompatActivity {

    String url = "https://www.google.com.hk/search?q=github+w446108264+MBLog&safe=strict&biw=1280&bih=636&tbm=isch&source=lnms&sa=X&ved=0ahUKEwin2pz5457MAhWF5KYKHWTVDRsQ_AUIBygC";

    String json = "{\"resultcode\":\"101\",\"result\":null,\"error_code\":10001}";

    double[][] doubles = {
            {1.2, 1.6, 1.7, 30, 33},
            {1.2, 1.6, 1.7, 30, 33},
            {1.2, 1.6, 1.7, 30, 33},
            {1.2, 1.6, 1.7, 30, 33}
    };

    public class User {
        private String name;
        private String sex;
        User(String name, String sex) {
            this.name = name;
            this.sex = sex;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_log) void btn_log() {

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("1", "hashMap1");
        hashMap.put("2", "hashMap2");
        hashMap.put("3", "hashMap3");
        hashMap.put("4", "hashMap4");

        L.d("NetTest", url, "\n" + json + "\n");

        L.i(2016);

        L.e(doubles, new User("jack", "f"), hashMap, "end");
    }

    @OnClick(R.id.btn_github) void btn_github() {
        Uri uri = Uri.parse("http://github.com/w446108264/MBLog");
        Intent it = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(it);
    }
}
