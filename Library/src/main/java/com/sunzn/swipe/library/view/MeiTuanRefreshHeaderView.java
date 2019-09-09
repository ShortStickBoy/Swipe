package com.sunzn.swipe.library.view;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.sunzn.swipe.library.R;
import com.sunzn.swipe.library.SwipeRefreshTrigger;
import com.sunzn.swipe.library.SwipeTrigger;

public class MeiTuanRefreshHeaderView extends RelativeLayout implements SwipeTrigger, SwipeRefreshTrigger {

    private ImageView ivRefresh;
    private LinearLayout llRefresh;

    private AnimationDrawable mAnimDrawable;

    private int mHeaderHeight;

    public MeiTuanRefreshHeaderView(Context context) {
        this(context, null);
    }

    public MeiTuanRefreshHeaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MeiTuanRefreshHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mHeaderHeight = context.getResources().getDimensionPixelOffset(R.dimen.refresh_header_height_100);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ivRefresh = findViewById(R.id.ivRefresh);
        llRefresh = findViewById(R.id.llRefresh);
        mAnimDrawable = (AnimationDrawable) ivRefresh.getBackground();
        if (!mAnimDrawable.isRunning()) {
            mAnimDrawable.start();
        }
    }

    @Override
    public void onRefresh() {
        if (!mAnimDrawable.isRunning()) {
            mAnimDrawable.start();
        }
    }

    @Override
    public void onPrepare() {
        llRefresh.setAlpha(0.3f);
        ivRefresh.setScaleX(0.4f);
        ivRefresh.setScaleY(0.4f);
        if (!mAnimDrawable.isRunning()) {
            mAnimDrawable.start();
        }
    }

    @Override
    public void onMove(int y, boolean isComplete, boolean automatic) {
        if (!isComplete) {
            float scale = (float) y / (float) mHeaderHeight;
            if (y >= mHeaderHeight) {
                ivRefresh.setScaleX(1);
                ivRefresh.setScaleY(1);
                llRefresh.setAlpha(1.0f);
            } else if (y > 0) {
                ivRefresh.setScaleX(scale);
                ivRefresh.setScaleY(scale);
                llRefresh.setAlpha(scale);
            } else {
                ivRefresh.setScaleX(0.4f);
                ivRefresh.setScaleY(0.4f);
                llRefresh.setAlpha(0.3f);
            }
        }
    }

    @Override
    public void onRelease() {
        mAnimDrawable.stop();
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onReset() {
        mAnimDrawable.stop();
        llRefresh.setAlpha(1.0f);
    }

    @Override
    public void onCancel() {

    }

}
