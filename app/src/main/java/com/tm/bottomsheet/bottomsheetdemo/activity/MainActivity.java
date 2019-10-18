package com.tm.bottomsheet.bottomsheetdemo.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ListView;

import com.tm.bottomsheet.bottomsheetdemo.BottomFragment;
import com.tm.bottomsheet.bottomsheetdemo.LvAdapter;
import com.tm.bottomsheet.bottomsheetdemo.MyBottomSheetDialog;
import com.tm.bottomsheet.bottomsheetdemo.R;

import static android.os.Build.VERSION_CODES.LOLLIPOP;
import static android.os.Build.VERSION_CODES.M;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private Toolbar toolbar;

    private int navigationBarColor;

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

        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.inflateMenu(R.menu.main);
            toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.menu_to_second:
                            startActivity(new Intent(MainActivity.this, Main2Activity.class));
                            return true;
                        case R.id.menu_to_third:
                            startActivity(new Intent(MainActivity.this, Main3Activity.class));
                            return true;
                    }
                    return false;
                }
            });
        }

        findViewById(R.id.btn_fragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomFragment fragment = new BottomFragment();
                fragment.show(getSupportFragmentManager(), "fragment");
            }
        });

        findViewById(R.id.btn_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initBottomSheetDialog();
            }
        });

        findViewById(R.id.btn_dialog_corners).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initBottomSheetDialog1();
            }
        });

        findViewById(R.id.btn_dialog_corners1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initBottomSheetDialogTransparent();
            }
        });

    }

    private void initBottomSheetDialog() {
        //当这个Dilaog打开再关闭后，无法用Dialog.show()再次打开，重新创建一个
        final BottomSheetDialog bottomSheetDialog  = new MyBottomSheetDialog(this);
        bottomSheetDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        View view = View.inflate(this, R.layout.dialog_fragment_list, null);
        ListView lv = view.findViewById(R.id.lv);
        lv.setAdapter(new LvAdapter(MainActivity.this));
        bottomSheetDialog.setContentView(view);

        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View) view.getParent());
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

        bottomSheetDialog.show();
    }

    private void initBottomSheetDialog1() {
        //当这个Dilaog打开再关闭后，无法用Dialog.show()再次打开，重新创建一个
        final BottomSheetDialog bottomSheetDialog  = new MyBottomSheetDialog(this);
        bottomSheetDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        View view = View.inflate(this, R.layout.dialog_corners, null);
        ListView lv = view.findViewById(R.id.lv);
        LvAdapter adapter = new LvAdapter(MainActivity.this);
        lv.setAdapter(adapter);
        bottomSheetDialog.setContentView(view);

        //固定高度 不可以expand
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.5);
        layoutParams.height = height;
        view.setLayoutParams(layoutParams);
        BottomSheetBehavior mBehavior = BottomSheetBehavior.from((View) view.getParent());
        mBehavior.setPeekHeight(height);

        //BottomSheetDialog背景是白色的，设置背景透明才能显示圆角
        //不在Support包下面  android.support.design.R.id.design_bottom_sheet；support包下 R.id.design_bottom_sheet
        bottomSheetDialog.getWindow().findViewById(R.id.design_bottom_sheet)
                .setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //给dialog换个动画试试
        bottomSheetDialog.getWindow().setWindowAnimations(R.style.DialogAnimBottom);

        bottomSheetDialog.show();
    }

    private void initBottomSheetDialogTransparent() {
        //当这个Dilaog打开再关闭后，无法用Dialog.show()再次打开，重新创建一个
        final BottomSheetDialog bottomSheetDialog  = new BottomSheetDialog(this, R.style.TransBottomSheetDialogStyle);

        View view = View.inflate(this, R.layout.dialog_single, null);
        View cancel = view.findViewById(R.id.tv_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetDialog.setContentView(view);

        //BottomSheetDialog背景是白色的，设置背景透明才能显示圆角
        bottomSheetDialog.getWindow().findViewById(R.id.design_bottom_sheet)
                .setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        bottomSheetDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setNavigationBarColor(navigationBarColor);
                }
            }
        });
        bottomSheetDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setNavigationBarColor(navigationBarColor);
                }
            }
        });

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//没有用
//            bottomSheetDialog.getWindow().setNavigationBarColor(getResources().getColor(R.color.white));
//        }

        bottomSheetDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    navigationBarColor = getWindow().getNavigationBarColor();
                    getWindow().setNavigationBarColor(getResources().getColor(R.color.white));
                }
            }
        });

        bottomSheetDialog.show();
    }


}
