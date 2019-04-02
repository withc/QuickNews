
package com.tiger.quicknews.wedget.gesture;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.FrameLayout;

import com.tiger.quicknews.R;
import com.tiger.quicknews.wedget.gesture.GestureViewGroup.GestureViewGroupGoneListener;

/**
 * 用于实现滑动关闭界面效果的辅助类 from SlideBaseActivity
 * 
 * @author xuewenchao
 */
public class SlideActivityHelper {
    private final Activity activity;
    private GestureViewGroup gesturellView;

    public SlideActivityHelper(Activity activity) {
        this.activity = activity;
    }

    protected void onCreate() {
        activity.overridePendingTransition(R.anim.slide_right_in, 0);
        activity.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        gesturellView = new GestureViewGroup(activity);
        gesturellView.setGestureViewGroupGoneListener(new GestureViewGroupGoneListener() {
            @Override
            public void onFinish() {
                activity.finish(); // 界面滑动消失后， Activity
            }
        });

    }

    void onResume() {
        FrameLayout decorView = (FrameLayout) activity.getWindow().getDecorView();
        View decorView_child = decorView.getChildAt(0);

        // 使用 GestureViewGroup 封装 DecorView 中的内容
        if (!(decorView_child instanceof GestureViewGroup)) {
            decorView.removeAllViews();
            decorView_child.setBackgroundResource(R.drawable.window_background);
            gesturellView.addView(decorView_child);

            decorView.addView(gesturellView);
        }
    }

    void finish() {
        activity.overridePendingTransition(0, R.anim.slide_right_out);
    }

}
