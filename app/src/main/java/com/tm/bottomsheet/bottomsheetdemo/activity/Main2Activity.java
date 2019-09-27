package com.tm.bottomsheet.bottomsheetdemo.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.tm.bottomsheet.bottomsheetdemo.R;
import com.tm.bottomsheet.bottomsheetdemo.utils.DensityUtils;

public class Main2Activity extends AppCompatActivity {

    private static final String TAG = "Main2Activity";

    private NestedScrollView nestedScrollView;
    private LinearLayout llContent;
    private ImageView ivHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        initBottomLayout();
    }

    private void initBottomLayout() {
        nestedScrollView = findViewById(R.id.layout_bottom);
        llContent = findViewById(R.id.ll_content);
        ivHeader = findViewById(R.id.iv_header);
        BottomSheetBehavior behavior = BottomSheetBehavior.from(findViewById(R.id.layout_bottom));

        getMarginLayoutParams(llContent).topMargin = DensityUtils.dp2px(Main2Activity.this, 30);
        llContent.requestLayout();

        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int i) {
                if (i == BottomSheetBehavior.STATE_EXPANDED) {
//                    nestedScrollView.scrollTo(0, DensityUtils.dp2px(MainActivity.this, 30));
                } else {
//                    nestedScrollView.scrollTo(0, DensityUtils.dp2px(MainActivity.this, 0));
                }
            }

            @Override
            public void onSlide(@NonNull View view, float v) {
//                Log.e(TAG, "onSlide: --->>" + v);
                ivHeader.setAlpha(1 - v);
                if (v > 0.95) {
                    ivHeader.setVisibility(View.GONE);

                    getMarginLayoutParams(llContent).topMargin = 0;
                    llContent.requestLayout();
                } else {
                    ivHeader.setVisibility(View.VISIBLE);

                    getMarginLayoutParams(llContent).topMargin = DensityUtils.dp2px(Main2Activity.this, 30);
                    llContent.requestLayout();
                }

            }
        });

    }


    private ViewGroup.MarginLayoutParams getMarginLayoutParams(View view) {
        return (ViewGroup.MarginLayoutParams) (view.getLayoutParams());
    }

    private void setMarginTop(View view, int marginTop) {
        Log.e(TAG, "setMarginTop: ----->>" + marginTop);
        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) view.getLayoutParams();
        lp.topMargin = marginTop;
        view.setLayoutParams(lp);
    }
}
