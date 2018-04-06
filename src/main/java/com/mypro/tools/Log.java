package com.mypro.tools;


import com.mypro.model.GamingInfo;

public class Log {
public static void e(String name, String info) {
    System.err.println("error:" + name + ":" + info);
}

public static void w(String name, String info) {
    System.out.println("warning:" + name + ":" + info);
}

/**
 * 把异常消息记录日志
 *
 * @param e
 */
public static void doLogForException(Exception e) {
    e.printStackTrace();
    //如果游戏结束了，那么不算是异常
    if (!GamingInfo.getGamingInfo().isGaming()) {
        return;
    }
    for (StackTraceElement ste : e.getStackTrace()) {
        Log.e(ste.getClassName() + ":", e.toString());
        Log.e("line:", ste.getLineNumber() + "");
        Log.e("method:", ste.getMethodName());
        Log.e("file:", ste.getFileName());
    }
}
}
