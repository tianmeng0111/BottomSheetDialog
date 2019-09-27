package com.tm.bottomsheet.bottomsheetdemo.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.SwipeDismissBehavior;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.tm.bottomsheet.bottomsheetdemo.R;
import com.tm.bottomsheet.bottomsheetdemo.behavior.MyBehavior;
import com.tm.bottomsheet.bottomsheetdemo.utils.DensityUtils;
import com.tm.bottomsheet.bottomsheetdemo.utils.ScreenUtils;

import static android.os.Build.VERSION_CODES.LOLLIPOP;

public class Main3Activity extends AppCompatActivity {

    private static final String TAG = "Main3Activity";

    private NestedScrollView nestedScrollView;
    private FrameLayout flBottomLayout;
    private ImageView ivHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(Build.VERSION.SDK_INT >= LOLLIPOP) {
            int uiFlags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));
//            getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow().getDecorView().setSystemUiVisibility(uiFlags);
        }
        setContentView(R.layout.activity_main3);

        nestedScrollView = findViewById(R.id.layout_bottom);
        flBottomLayout = findViewById(R.id.fl_bottom_layout);
        ivHeader = findViewById(R.id.iv_header);

        MyBehavior behavior = MyBehavior.from(nestedScrollView);
        behavior.setAnchorPoint((ScreenUtils.getScreenHeight(Main3Activity.this)
                - ScreenUtils.getStatusHeight(Main3Activity.this)
                - DensityUtils.dp2px(Main3Activity.this, 48)) / 2);
        behavior.setState(MyBehavior.STATE_COLLAPSED);
        behavior.addBottomSheetCallback(new MyBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == MyBehavior.STATE_EXPANDED) {
//                    ivHeader.setVisibility(View.GONE);

//                    setMarginTop(ivHeader, DensityUtils.dp2px(Main3Activity.this, 50));
                } else if (newState == MyBehavior.STATE_ANCHOR_POINT){
//                    ivHeader.setVisibility(View.VISIBLE);

//                    setMarginTop(ivHeader, 0);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, final float slideOffset) {
                //用渐变
//                ivHeader.setAlpha((1 - slideOffset) * 2);
                //用平移
//                Log.e(TAG, "onSlide: ------->>" +  ((slideOffset - 0.5) * DensityUtils.dp2px(Main3Activity.this, 100)));
//                getMarginLayoutParams(ivHeader).topMargin = (int) ((slideOffset - 0.5) * DensityUtils.dp2px(Main3Activity.this, 100));
//                ivHeader.requestLayout();
//                getMarginLayoutParams(ivHeader).leftMargin = (int) (slideOffset * DensityUtils.dp2px(Main3Activity.this, 30) / -0.5);
                if (slideOffset > 0.5) {
                    setMarginTop(ivHeader, (int) ((slideOffset - 0.5) * DensityUtils.dp2px(Main3Activity.this, 100)));
                    setMarginLeft(ivHeader, (int) (
                            (slideOffset - 0.5) *
                                    (DensityUtils.dp2px(Main3Activity.this, 20) - DensityUtils.dp2px(Main3Activity.this, 50))
                                    / (1 - 0.5) +
                                    DensityUtils.dp2px(Main3Activity.this, 50)
                    ));
                }
            }
        });

//        SwipeDismissBehavior
    }

    private ViewGroup.MarginLayoutParams getMarginLayoutParams(View view) {
        return (ViewGroup.MarginLayoutParams) (view.getLayoutParams());
    }

    private void setMarginTop(View view, int marginTop) {
        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) view.getLayoutParams();
        lp.topMargin = marginTop;
        view.setLayoutParams(lp);
    }

    private void setMarginLeft(View view, int marginLeft) {
        Log.e(TAG, "setMarginTop: ----->>" + marginLeft);
        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) view.getLayoutParams();
        lp.leftMargin = marginLeft;
        view.setLayoutParams(lp);
    }
}
