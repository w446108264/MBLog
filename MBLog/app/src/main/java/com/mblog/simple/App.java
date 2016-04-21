package com.mblog.simple;

import android.app.Application;

import sj.mblog.L;
import sj.mblog.MBPrinter;
import sj.mblog.parser.JsonParser;
import sj.mblog.parser.ObjectParser;
import sj.mblog.parser.UrlParser;

/**
 * Created by sj on 16/4/20.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        /**
         * 这是非必须的
         * 如果你什么也不做,输出Log时默认按如下配置初始化
         */
        L.initPrinter(new MBPrinter())
                .setTag("MBLog")
                .setPrint(true)
                .setParserList(new JsonParser(), new UrlParser(), new ObjectParser());

        /**
         * 支持在程序中动态的改变print的设置
         */
        L.setTag("MBLog");
        L.setPrint(true);
        L.setParserList(new JsonParser(), new UrlParser(), new ObjectParser());
    }
}
