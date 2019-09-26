package com.tm.bottomsheet.bottomsheetdemo;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class BottomFragment extends BottomSheetDialogFragment {

    private static final String TAG = "BottomFragment";

    private BottomSheetDialog dialog;

    private ListView lv;
    private LvAdapter adapter;

    private BottomSheetBehavior behavior;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        dialog = new MyBottomSheetDialog(getContext());
        View view = View.inflate(getContext(), R.layout.dialog_fragment_list, null);
        dialog.setContentView(view);
        initView(view);
        behavior = BottomSheetBehavior.from((View) view.getParent());
        initBehavior();
        return dialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //不好获取behavior
    //如果这两个方法都重写了，那么以onCreateView里的视图为准，onCreateDialog会被覆盖。
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.dialog_fragment_list, container);
//        lv = view.findViewById(R.id.lv);
//        adapter = new LvAdapter(getActivity());
//        lv.setAdapter(adapter);
//
//        initBehavior(view);
//        return view;
//    }

    private void initView(View view) {
        lv = view.findViewById(R.id.lv);
        lv.setAdapter(new LvAdapter(getContext()));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        });
    }

    private void initBehavior() {
        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int i) {
                switch (i) {
                    case BottomSheetBehavior.STATE_HIDDEN:
                        Log.e(TAG, "onStateChanged: ======================STATE_HIDDEN");
                        dialog.dismiss();
                        break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        Log.e(TAG, "onStateChanged: ======================STATE_DRAGGING");
                        break;
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        Log.e(TAG, "onStateChanged: ======================STATE_COLLAPSED");
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        Log.e(TAG, "onStateChanged: ======================STATE_EXPANDED");
                        break;
                }

                if (i == BottomSheetBehavior.STATE_EXPANDED) {
//                    if(Build.VERSION.SDK_INT >= LOLLIPOP) {
//                        getActivity().getWindow().clearFlags(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//                        int uiFlags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
//                        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//                        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//                        getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.white));
//                        getActivity().getWindow().getDecorView().setSystemUiVisibility(uiFlags);
//                        getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR | View.SYSTEM_UI_FLAG_VISIBLE);
//                    }
//                    Window window = dialog.getWindow();
//                    WindowManager.LayoutParams lp = window.getAttributes();
//                    lp.dimAmount = 0;
//                    window.setAttributes(lp);
                } else {
//                    if(Build.VERSION.SDK_INT >= LOLLIPOP) {
//                        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//                        getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));
//                    }
//                    Window window = dialog.getWindow();
//                    WindowManager.LayoutParams lp = window.getAttributes();
//                    lp.dimAmount = 0.6f;//默认就是0.6
//                    window.setAttributes(lp);
                }
//                Log.e(TAG, "onStateChanged: ---->>" + i);
            }

            @Override
            public void onSlide(@NonNull View view, float v) {
//                Log.e(TAG, "onSlide: " + v);
            }
        });

        behavior.setHideable(true);
        //不用这个用设置PeekHeight
//        behavior.setState(BottomSheetBehavior.STATE_HALF_EXPANDED);
//        behavior.setPeekHeight(ScreenUtils.getScreenHeight(getContext()) / 2);
//        behavior.setSkipCollapsed(false);//？
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
//        if(Build.VERSION.SDK_INT >= LOLLIPOP) {
//            getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));
//        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
//        if(Build.VERSION.SDK_INT >= LOLLIPOP) {
//            getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));
//        }
    }
}
