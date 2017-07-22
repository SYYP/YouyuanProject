package yipengyu.baway.com.youyuanproject.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * date : ${Data}
 * author:衣鹏宇(ypu)
 */

public class Networkutil {
    //判断网络是否有连接
    public  static boolean isNetworkConnected(Context context) {
             if (context != null) {
                    ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                            .getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
                    if (mNetworkInfo != null) {
                        return true;
                        }
                }
             return false;
         }
    //判断WIFI网络是否可用
    public static boolean isWifiConnected(Context context) {
             if (context != null) {
                     ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                            .getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo mWiFiNetworkInfo = mConnectivityManager
                             .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                    if (mWiFiNetworkInfo != null) {
                             return true;
                         }
             }
             return false;
         }

}
