package sj.mblog;

import android.text.TextUtils;

import java.util.LinkedList;

import sj.mblog.parser.Parser;

/**
 * Created by sj on 16/4/13.
 */
public class L {

    public static final String LOG_TAG_DEFUALT = "MBLog";

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

    public static Printer sMBPrinter;

    public static void d(Object... args) {
        getPrinter().d(args);
    }

    public static void e(Object... args) {
        getPrinter().e(args);
    }

    public static void e(Throwable throwable, Object... args) {
        getPrinter().e(throwable, args);
    }

    public static void w(Object... args) {
        getPrinter().w(args);
    }

    public static void i(Object... args) {
        getPrinter().i(args);
    }

    public static void v(Object... args) {
        getPrinter().v(args);
    }

    public static void wtf(Object... args) {
        getPrinter().wtf(args);
    }

    public static Printer getPrinter() {
        if (sMBPrinter != null) {
            return sMBPrinter;
        }
        sMBPrinter = new MBPrinter();
        sMBPrinter.init();
        return sMBPrinter;
    }

    public static Builder initPrinter(Printer printer) {
        if (printer != null) {
            sMBPrinter = printer;
            return sMBPrinter.init();
        }
        sMBPrinter = sMBPrinter == null ? new MBPrinter() : sMBPrinter;
        return sMBPrinter.init();
    }

    public static void setTag(String tag) {
        getPrinter().getLogBuilder().setTag(tag);
    }

    public static void setPrint(PRINT printType) {
        getPrinter().getLogBuilder().setPrint(printType);
    }

    public static void setParserList(Parser... parsers) {
        getPrinter().getLogBuilder().setParserList(parsers);
    }

    public static void setLastMethodClassName(String className) {
        getPrinter().setLastMethodClassName(className);
    }

    public static class Builder {

        public Builder setTag(String tag) {
            this.tag = tag;
            if (TextUtils.isEmpty(tag)) {
                this.tag = LOG_TAG_DEFUALT;
            }
            return this;
        }

        public Builder setPrint(PRINT printType) {
            this.printType = printType;
            return this;
        }

        public Builder setParserList(Parser... parsers) {
            if (this.parserList == null) {
                this.parserList = new LinkedList<>();
            }
            this.parserList.clear();
            if (parsers == null) {
                return this;
            }
            for (Parser parser : parsers) {
                this.parserList.add(parser);
            }
            return this;
        }

        protected String tag;
        protected PRINT printType = PRINT.MBLOG;
        protected LinkedList<Parser> parserList;

        public Builder() {
        }
    }
}
