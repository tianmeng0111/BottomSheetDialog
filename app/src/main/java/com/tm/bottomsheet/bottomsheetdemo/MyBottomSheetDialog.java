package com.tm.bottomsheet.bottomsheetdemo;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.ViewGroup;

import com.tm.bottomsheet.bottomsheetdemo.utils.DensityUtils;
import com.tm.bottomsheet.bottomsheetdemo.utils.ScreenUtils;

/**
 * 在7.0以上 状态栏不变黑
 */

public class MyBottomSheetDialog extends BottomSheetDialog {

    public MyBottomSheetDialog(@NonNull Context context) {
        super(context);
    }

    public MyBottomSheetDialog(@NonNull Context context, int theme) {
        super(context, theme);
    }

    protected MyBottomSheetDialog(@NonNull Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        int screenHeight = getScreenHeight(getOwnerActivity());
        int screenHeight = ScreenUtils.getScreenHeight(getContext());
//        int statusBarHeight = getStatusBarHeight(getContext());
        //这里不知道为什么 需要48dp
//        int statusBarHeight = ScreenUtils.getStatusHeight(getContext()) - DensityUtils.dp2px(getContext(), 48);
//        int dialogHeight = screenHeight - statusBarHeight;
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, screenHeight == 0 ? ViewGroup.LayoutParams.MATCH_PARENT : screenHeight);
        getWindow().setGravity(Gravity.TOP);
    }

    private static int getScreenHeight(Activity activity) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        return displaymetrics.heightPixels;
    }

    /**
     * 取的不准确 比实际的高
     * @param context
     * @return
     */
    private static int getStatusBarHeight(Context context) {
        int statusBarHeight = 0;
        Resources res = context.getResources();
        int resourceId = res.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = res.getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }
}
