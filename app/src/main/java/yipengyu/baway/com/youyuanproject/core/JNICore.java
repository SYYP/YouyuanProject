package yipengyu.baway.com.youyuanproject.core;

import com.getkeepsafe.relinker.ReLinker;

import yipengyu.baway.com.youyuanproject.base.IApplication;

/**
 * Created by muhanxi on 17/7/4.
 */

public class JNICore {

    static {
        // System.load("libcore");
        //   System.loadLibrary("core");
        ReLinker.loadLibrary(IApplication.getApplication(), "core");
    }

    public static native String getSign(String sign);

}
