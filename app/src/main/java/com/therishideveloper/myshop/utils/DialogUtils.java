package com.therishideveloper.myshop.utils;

/*
    Created by Shuva Ranjan Rishi on 12/31/2022
*/

import android.app.AlertDialog;
import android.content.Context;
import android.text.Html;

import com.therishideveloper.myshop.R;

public class DialogUtils {

        public static void showNoInternetDialog(Context mContext) {
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setIcon(R.drawable.ic_signal_wifi);
            builder.setTitle("No Internet!");
            builder.setMessage("Plz turn on your Internet...");
            builder.setCancelable(true);
            builder.setPositiveButton(Html.fromHtml("<font color='#35a605'>" + "OK" + "</font>"), (dialog, which) -> dialog.dismiss());
            builder.show();
        }
}
