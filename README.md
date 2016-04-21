# MBLog
 
> j.s 🇨🇳

MBLog,一个神奇的Log.支持输入任意类型;支持输出自动格式化xml，json，url，object;支持输出可跳转的Log函数定位信息;支持自定义输出样式

<img src="https://github.com/w446108264/MBLog/raw/master/output/drymartine.jpg" width="100%" /> 

# Features

* API > 9 
* 支持输入任意类型
* 支持输出自动格式化xml，json，url，object
* 支持输出可跳转的Log函数定位信息
* 支持自定义输出样式 
 
# 格式化效果
<img src="https://github.com/w446108264/MBLog/raw/master/output/format.png"/> 

# 使用

```java
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        /**
         * 这是非必须的
         * 如果你什么也不做,输出Log时默认按如下配置初始化
         */
        L.initPrinter(new MBPrinter())
                // Tag
                .setTag("MBLog")
                // print输出开关
                .setPrint(true)
                // 增加Parser，MBLog会依次根据Parser增加的顺序识别输入的内容。
                // 内容识别并格式化后即结束
                // 无法识别的内容转换成字符串
                .setParserList(new JsonParser(), new UrlParser(), new ObjectParser());

        /**
         * 支持在程序中动态的改变print的设置
         */
        L.setTag("MBLog");
        L.setPrint(true);
        L.setParserList(new JsonParser(), new UrlParser(), new ObjectParser());
    }
}
```

```java
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("1", "hashMap1");
        hashMap.put("2", "hashMap2");
        hashMap.put("3", "hashMap3");
        hashMap.put("4", "hashMap4");

        // 示例一次网络Log
        // URL + JSON
        L.d("NetTest", url, "\n" + json + "\n");

        L.i(2016);

        // 示例Object输出
        L.e(doubles, new User("jack", "f"), hashMap, "end");
```

# 方法
 * L.d(Object... args);

支持任意Object，参数间通过换行分割

<img src="https://github.com/w446108264/MBLog/raw/master/output/666.jpg" /> 

 * L.e(Object... args);
 * L.e(Throwable throwable, Object... args);
 * L.w(Object... args);
 * L.i(Object... args);
 * L.v(Object... args);
 * L.wtf(Object... args);

# 其他
如果你想要自定义输出风格，可以自定义Printer，并初始化它

 * L.initPrinter(new MBPrinter())

# Gradle Dependency

Users of your library will need add the jitpack.io repository:

```xml  
allprojects {
    repositories {
        jcenter()
        maven { url "https://jitpack.io" }
    }
}
```

and:

```xml
dependencies { 
    compile 'com.github.w446108264:MBLog:1.0.0'
}
```

# Thanks

* [https://github.com/orhanobut/logger](https://github.com/orhanobut/logger)
* [https://github.com/tianzhijiexian/logger](https://github.com/tianzhijiexian/logger)


# Contact & Help

Please fell free to contact me if there is any problem when using the library.

* email: shengjun8486@gmail.com 

