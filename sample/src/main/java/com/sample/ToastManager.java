package com.sample;

import android.content.Context;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.widget.Toast;

public class ToastManager {

    private Context mContext;
    private Toast mToast;

    @SuppressWarnings("ShowToast")
    private ToastManager() {
        mContext = Application.getContext();
        mToast = Toast.makeText(mContext, "", Toast.LENGTH_SHORT);
    }

    public static ToastManager getInstance() {
        return SingleHolder.instance;
    }

    public void showToast(@StringRes int resId) {
        showToast(mContext.getResources().getString(resId));
    }

    public void showToast(String tip) {
        if (!TextUtils.isEmpty(tip)) {
            mToast.setText(tip);
            mToast.show();
        }
    }

    private static class SingleHolder {
        private static ToastManager instance = new ToastManager();
    }
}
