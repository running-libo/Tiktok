package com.bytedance.tiktok.base;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.DialogFragment;
import com.bytedance.tiktok.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.gyf.immersionbar.ImmersionBar;

/**
 * create by libo
 * create on 2020/5/20
 * description 全屏下拉弹框基类
 */
public class BaseFullBottomSheetFragment extends BottomSheetDialogFragment {
    public BottomSheetBehavior<FrameLayout> behavior;
    private FrameLayout bottomSheet;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.MyDialog);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImmersionBar.with(this).navigationBarColor(R.color.color_bg_theme).init();
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//
//        // 设置软键盘不自动弹出
//        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
//        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
//
//        BottomSheetDialog dialog = (BottomSheetDialog) getDialog();
//        bottomSheet = dialog.getDelegate().findViewById(android.support.design.R.id.design_bottom_sheet);
//        if (bottomSheet != null) {
//            CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) bottomSheet.getLayoutParams();
//            layoutParams.height = getHeight();
//            bottomSheet.setLayoutParams(layoutParams);
//            behavior = BottomSheetBehavior.from(bottomSheet);
//            behavior.setPeekHeight(getHeight());
//            // 初始为展开状态
//            behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
//        }
//
//        // 重写返回键 用于回退
//        DialogInterface.OnKeyListener keylistener = (dialog1, keyCode, event) -> {
//            if (event.getAction() == KeyEvent.ACTION_UP) {
//                return onKeyDown(dialog1, keyCode, event);
//            }
//            return false;
//
//        };
//        getDialog().setOnKeyListener(keylistener);
//    }

    /**
     * 用于返回键使用
     *
     * @param dialog
     * @param keyCode
     * @param event
     * @return
     */
    protected boolean onKeyDown(DialogInterface dialog, int keyCode, KeyEvent event) {
        return false;
    }

    /**
     * 是否可以拖拽
     *
     * @param canDrag
     */
    public void setCanDrag(boolean canDrag) {
        getDialog().setCancelable(canDrag);
    }

    /**
     * 获取屏幕高度
     *
     * @return height
     */
    protected int getHeight() {
        return getScreenHeight(getContext());
    }

    public BottomSheetBehavior<FrameLayout> getBehavior() {
        return behavior;
    }

    /**
     * 获取屏幕高度
     *
     * @param context
     * @return
     */
    public int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

}
