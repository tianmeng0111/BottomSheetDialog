package com.tm.bottomsheet.bottomsheetdemo.activity;

import android.graphics.Color;
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
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.tm.bottomsheet.bottomsheetdemo.R;
import com.tm.bottomsheet.bottomsheetdemo.behavior.MyBehavior;
import com.tm.bottomsheet.bottomsheetdemo.utils.DensityUtils;
import com.tm.bottomsheet.bottomsheetdemo.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.os.Build.VERSION_CODES.LOLLIPOP;

public class Main3Activity extends AppCompatActivity {

    private static final String TAG = "Main3Activity";

    private NestedScrollView nestedScrollView;
    private FrameLayout flBottomLayout;
    private ImageView ivHeader;
    private ImageView ivBg;
    private TextView tvTitle;
    private LinearLayout llContent;

    private ListView lv;
    private List<String> list;

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

        lv = findViewById(R.id.lv);

        ivBg = findViewById(R.id.iv_bg);
        tvTitle = findViewById(R.id.tv_title);
        llContent = findViewById(R.id.ll_content);

        final MyBehavior behavior = MyBehavior.from(nestedScrollView);
//        behavior.setAnchorPoint(
//                (ScreenUtils.getScreenHeight(Main3Activity.this)
//                - ScreenUtils.getStatusHeight(Main3Activity.this)
//                - DensityUtils.dp2px(Main3Activity.this, 48)) / 2);
        ivBg.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                behavior.setAnchorPoint(
                        ScreenUtils.getScreenHeight(Main3Activity.this)
                        - DensityUtils.dp2px(Main3Activity.this, 30) //半个头像高度
                        - ScreenUtils.getStatusHeight(Main3Activity.this)
                        - ivBg.getMeasuredHeight());
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    ivBg.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            }
        });

        behavior.addBottomSheetCallback(new MyBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == MyBehavior.STATE_EXPANDED) {
//                    Log.e(TAG, "onStateChanged: ---------STATE_EXPANDED");
                    setMarginTop(llContent, 0);
                } else if (newState == MyBehavior.STATE_ANCHOR_POINT){
//                    Log.e(TAG, "onStateChanged: ---------STATE_ANCHOR_POINT");
                    setMarginTop(llContent, DensityUtils.dp2px(Main3Activity.this, 30));
                }

//                if (newState == MyBehavior.STATE_COLLAPSED) {
//                    Log.e(TAG, "onStateChanged: ---------STATE_COLLAPSED");
//                    tvTitle.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                } else {
//                    tvTitle.setBackgroundColor(Color.TRANSPARENT);
//                }
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
                    setMarginTop(ivHeader, (int) ((slideOffset - 0.5) * DensityUtils.dp2px(Main3Activity.this, 30)));
                    setMarginLeft(ivHeader, (int) (
                            (slideOffset - 0.5) *
                                    (DensityUtils.dp2px(Main3Activity.this, 20) - DensityUtils.dp2px(Main3Activity.this, 50))
                                    / (1 - 0.5) +
                                    DensityUtils.dp2px(Main3Activity.this, 50)
                    ));

                    int dp30 = DensityUtils.dp2px(Main3Activity.this, 30);
//                    Log.e(TAG, "onSlide: " + slideOffset + "----->>" + ( ((slideOffset - 0.5 )* -dp30 / (1-0.5)) + dp30) );
                    setMarginTop(llContent, (int) ( ((slideOffset - 0.5 )* -dp30 / (1-0.5)) + dp30) );
                } else {
//                    if (getMarginLayoutParams(llContent).topMargin != DensityUtils.dp2px(Main3Activity.this, 30)) {
//                        setMarginTop(llContent, DensityUtils.dp2px(Main3Activity.this, 30));
//                    }
                }

//                if (slideOffset > 0.95) {
//                    getMarginLayoutParams(llContent).topMargin = 0;
//                    llContent.requestLayout();
//                } else {
//                    getMarginLayoutParams(llContent).topMargin = DensityUtils.dp2px(Main3Activity.this, 30);
//                    llContent.requestLayout();
//                }
            }
        });

        behavior.setState(MyBehavior.STATE_COLLAPSED);
//        tvTitle.setBackgroundColor(getResources().getColor(R.color.colorPrimary));


//        setData();
    }

    private void setData() {
        list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add(i + "");
        }
        lv.setAdapter(new ArrayAdapter<String>(Main3Activity.this, R.layout.support_simple_spinner_dropdown_item, list));
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
//        Log.e(TAG, "setMarginTop: ----->>" + marginLeft);
        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) view.getLayoutParams();
        lp.leftMargin = marginLeft;
        view.setLayoutParams(lp);
    }
}
