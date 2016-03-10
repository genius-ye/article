# 日志工具类

#### 源码：

```java

package com.genius.logger.utils;

import android.util.Log;

/**
 * Created on 2016/3/10.
 * <p/>
 * author : genius-ye
 * <p/>
 * 1、Log.v 的调试颜色为黑色的，任何消息都会输出，这里的v代表verbose啰嗦的意思，平时使用就是Log.v("","");
 * <p/>
 * 2、Log.d的输出颜色是蓝色的，仅输出debug调试的意思，但他会输出上层的信息，过滤起来可以通过DDMS的Logcat标签来选择.
 * <p/>
 * 3、Log.i的输出为绿色，一般提示性的消息information，它不会输出Log.v和Log.d的信息，但会显示i、w和e的信息
 * <p/>
 * 4、Log.w的意思为橙色，可以看作为warning警告，一般需要我们注意优化Android代码，同时选择它后还会输出Log.e的信息。
 * <p/>
 * 5、Log.e为红色，可以想到error错误，这里仅显示红色的错误信息，这些错误就需要我们认真的分析，查看栈的信息了
 * <p/>
 * Android log 的级别 ：v -> d -> i -> w -> e
 */
public class Logger {

    private static final String TAG = "Logger_Debug";

    /**
     * 开关——是否打印log
     **/
    private static final boolean isDebug = true;

    /**
     * 打印提示信息
     *
     * @param TAG
     * @param msg
     */
    public static void v(String TAG, String msg) {
        if (isDebug) {
            Log.v(TAG, msg);
        }
    }

    public static void v(String msg) {
        if (isDebug) {
            Log.v(TAG, msg);
        }
    }

    /**
     * 打印调试信息
     *
     * @param TAG
     * @param msg
     */
    public static void d(String TAG, String msg) {
        if (isDebug) {
            Log.d(TAG, msg);
        }
    }

    public static void d(String msg) {
        if (isDebug) {
            Log.d(TAG, msg);
        }
    }

    /**
     * 打印提示信息
     *
     * @param TAG
     * @param msg
     */
    public static void i(String TAG, String msg) {
        if (isDebug) {
            Log.i(TAG, msg);
        }
    }

    public static void i(String msg) {
        if (isDebug) {
            Log.i(TAG, msg);
        }
    }

    /**
     * 打印警告信息
     *
     * @param TAG
     * @param msg
     */
    public static void w(String TAG, String msg) {
        if (isDebug) {
            Log.w(TAG, msg);
        }
    }

    public static void w(String msg) {
        if (isDebug) {
            Log.w(TAG, msg);
        }
    }

    /**
     * 打印错误信息
     *
     * @param TAG
     * @param msg
     */
    public static void e(String TAG, String msg) {
        if (isDebug) {
            Log.e(TAG, msg);
        }
    }

    public static void e(String msg) {
        if (isDebug) {
            Log.e(TAG, msg);
        }
    }


}
```

> 在项目中尽量使用该类，当项目上线时可以通过设置 `isDebug = false` 来清除日志
