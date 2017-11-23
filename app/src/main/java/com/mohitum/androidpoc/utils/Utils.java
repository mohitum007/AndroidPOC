package com.mohitum.androidpoc.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Utility class for universal utility methods
 * Created by mohitum on 23-11-2017.
 *
 * @version 1.0
 */
public class Utils {

    /**
     * Check if internet connection is available or not
     *
     * @param mContext calling context of the application
     * @return true if internet is available else false
     */
    public static boolean isInternetConnectionAvailable(Context mContext) {
        if (null == mContext) {
            return true;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        // test for connection
        NetworkInfo netInfo = null;
        if (null != connectivityManager) {
            netInfo = connectivityManager.getActiveNetworkInfo();
        }
        return (null != netInfo && netInfo.isAvailable() && netInfo.isConnected());
    }
}
