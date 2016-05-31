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

    String json = "{\"error_code\":\"0\",\"error_msg\":\"\",\"data\":{\"user_id\":395569,\"username\":\"\\u857e\\u9635\\u5b50\",\"avatar\":\"http:\\/\\/avatar.image.com\\/FkaM-V8n9V-jwCFHiAlQ4nDSfgg_?imageView2\\/1\\/w\\/200\\/h\\/200\",\"list\":[{\"id\":\"174979\",\"user_id\":\"395414\",\"username\":\"\\u9c81\\u742a\\u742a\\u742a\\u742a\\u742a\\u742a\\u742a\\u4e36\",\"avatar\":\"http:\\/\\/tp2.sinaimg.cn\\/2145948257\\/50\\/5722834057\\/0\",\"is_followed\":\"0\"},{\"id\":\"174935\",\"user_id\":\"395576\",\"username\":\"\\u957f\\u9888\\u9e7f\\u541b395576\",\"avatar\":\"http:\\/\\/q.qlogo.cn\\/qqapp\\/1103279332\\/1A67C53BE84BD126A3047497DF05BDCB\\/100\",\"is_followed\":\"0\"},{\"id\":\"174889\",\"user_id\":\"395504\",\"username\":\"hshjzjajajwbhzhzhw\",\"avatar\":\"http:\\/\\/tp2.sinaimg.cn\\/5112186889\\/180\\/5714551813\\/0\",\"is_followed\":\"0\"}],\"total\":1}}";

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

        findViewById(R.id.btn_log).postDelayed(new Runnable() {
            @Override
            public void run() {
                L.setPrint(L.PRINT.SYSTEM);

                L.i("defalut log");
            }
        }, 1000);

    }

    @OnClick(R.id.btn_github) void btn_github() {
        Uri uri = Uri.parse("http://github.com/w446108264/MBLog");
        Intent it = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(it);
    }
}
