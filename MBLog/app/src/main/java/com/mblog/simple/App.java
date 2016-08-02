package com.mblog.simple;

import android.app.Application;

import com.facebook.stetho.Stetho;

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
                .setPrint(L.PRINT.MBLOG)
                .setParserList(new JsonParser(), new UrlParser(), new ObjectParser());

        /**
         * 支持在程序中动态的改变print的设置
         */
        L.setTag("MBLog");
        L.setPrint(L.PRINT.MBLOG);
        L.setParserList(new JsonParser(), new UrlParser(), new ObjectParser());


        /**
         * 支持chrome输出log调试
         * 建议使用单独的buildtype, 确保release版本不包含stetho
         */
        initStetho();
        L.initPrinter(new SuperMbPrinter());
    }

    private void initStetho() {
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }
}
