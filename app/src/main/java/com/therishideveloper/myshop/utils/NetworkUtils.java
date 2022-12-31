package com.therishideveloper.myshop.utils;

/*
    Created by Shuva Ranjan Rishi on 12/31/2022
*/

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtils {

    public static boolean checkInternet(Context context) {

        final ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connMgr != null) {
            NetworkInfo activeNetworkInfo = connMgr.getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) { // connected to the internet
                if (activeNetworkInfo.isAvailable()) {
                    if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI || activeNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
