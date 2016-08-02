package com.mblog.simple;

import android.util.Log;

import com.facebook.stetho.inspector.console.CLog;
import com.facebook.stetho.inspector.console.ConsolePeerManager;
import com.facebook.stetho.inspector.protocol.module.Console;

import sj.mblog.MBPrinter;

/**
 * Created by sj on 16/4/25.
 */
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
