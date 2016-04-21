package sj.mblog;

public interface Printer {

    L.Builder init();

    L.Builder getLogBuilder();

    void d(Object... args);

    void e(Object... args);

    void e(Throwable throwable, Object... args);

    void w(Object... args);

    void i(Object... args);

    void v(Object... args);

    void wtf(Object... args);
}
