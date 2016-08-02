# MBLog
 
> j.s 🇨🇳

MBLog,一个神奇的Log.支持chrome输出Log,支持输入任意类型;支持输出自动格式化xml，json，url，object;支持输出可跳转的Log函数定位信息;支持自定义输出样式

<img src="https://github.com/w446108264/MBLog/raw/master/output/drymartine.jpg" width="100%" /> 

# Features

* API > 9 
* 支持输入任意类型
* 支持可变参数
* 支持输出自动格式化xml，json，url，object
* 支持自定义格式Parser
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
                .setPrint(L.PRINT.MBLOG)
                // 增加Parser，MBLog会依次根据Parser增加的顺序识别输入的内容。
                // 内容识别并格式化后即结束
                // 无法识别的内容转换成字符串
                // 支持自定义Parser规则样式
                .setParserList(new JsonParser(), new UrlParser(), new ObjectParser());

        /**
         * 支持在程序中动态的改变print的设置
         */
        L.setTag("MBLog");
        L.setPrint(L.PRINT.SYSTEM);
        L.setParserList(new JsonParser());
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

### 自定义输出风格
如果你想要自定义输出风格，可以自定义Printer，并初始化它

 * L.initPrinter(new MBPrinter())

### 选择打印模式

你可以动态的改变输出模式

 * L.setPrint(L.PRINT.MBLOG);

 
```java
 public enum PRINT {
        /**
         * 不打印Log
         */
        NONE,
        /**
         * 使用系统默认Log打印
         */
        SYSTEM,
        /**
         * 使用MBLog格式化打印
         */
        MBLOG,
        /**
         * 使用MBLog格式化打印,但只打印内容,不打印方法和线程等信息
         */
        MBLOG_NOMETHOD
 }
```

### 基于MBLog的封装

如果你希望基于MBLog额外封装一层Log,以防止由于第三方库的变化影响项目

* L.setLastMethodClassName("com.mblog.simple.SimpleLog");

```java
public class SimpleLog {

    /**
     * 假如希望在MBLog上封装一层自己的log
     * 那么你需要额外设置最后一个方法偏移的类名
     */
    static {
        L.setLastMethodClassName("com.mblog.simple.SimpleLog");
    }

    public static void d(Object... args) {
        L.d(args);
    }

    public static void e(Object... args) {
        L.e(args);
    }

    public static void e(Throwable throwable, Object... args) {
        L.e(throwable, args);
    }

    public static void w(Object... args) {
        L.w(args);
    }

    public static void i(Object... args) {
        L.i(args);
    }

    public static void v(Object... args) {
        L.v(args);
    }

    public static void wtf(Object... args) {
        L.wtf(args);
    }
}
```

```java
   SimpleLog.i("Test","Test","Test");
```

### 基于Stetho在chrome的console输出log
 
1.引入stetho 

```xml
dependencies { 
    debugCompile 'com.facebook.stetho:stetho:1.3.1'
}
```

2.自定义MBPrinter

```java
public class SuperMbPrinter extends MBPrinter {

    ConsolePeerManager sPeerManager;

    public SuperMbPrinter() {
        sPeerManager = ConsolePeerManager.getInstanceOrNull();
    }

    protected void print(int priority, String tag, String chunk) {
        super.print(priority, tag, chunk);

        if (sPeerManager == null) {
            sPeerManager = ConsolePeerManager.getInstanceOrNull();
        }

        Console.MessageLevel logLevel;

        switch (priority) {
            case Log.VERBOSE:
            case Log.DEBUG:
                logLevel = Console.MessageLevel.DEBUG;
                break;
            case Log.INFO:
                logLevel = Console.MessageLevel.LOG;
                break;
            case Log.WARN:
                logLevel = Console.MessageLevel.WARNING;
                break;
            case Log.ERROR:
            case Log.ASSERT:
                logLevel = Console.MessageLevel.ERROR;
                break;
            default:
                logLevel = Console.MessageLevel.LOG;
        }

        CLog.writeToConsole(
                logLevel,
                Console.MessageSource.OTHER,
                chunk
        );
    }
}
```

3.使用Printer

```java
        /**
         * 支持chrome输出log调试
         * 建议使用单独的buildtype, 确保release版本不包含stetho
         */
        Stetho.initialize(Stetho.newInitializerBuilder(this)
              .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
              .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
              .build());
              
        L.initPrinter(new SuperMbPrinter());
```
 
 
# You can [download a sample APK](https://github.com/w446108264/MBLog/raw/master/output/app-debug.apk) 
 
4.chrome浏览器输入chrome://inspect/#devices

5.inspect

<img src="https://github.com/w446108264/MBLog/raw/master/output/inspect.png"/> 

6.在console获得log输出

<img src="https://github.com/w446108264/MBLog/raw/master/output/chrome_console_log.png"/> 



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
    compile 'com.github.w446108264:MBLog:1.0.3'
}
```
  
 
# Thanks

* [https://github.com/orhanobut/logger](https://github.com/orhanobut/logger)
* [https://github.com/tianzhijiexian/logger](https://github.com/tianzhijiexian/logger)


# Contact & Help

Please fell free to contact me if there is any problem when using the library.

* email: shengjun8486@gmail.com 

