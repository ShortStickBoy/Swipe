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

public class NoteRefreshHeaderView extends RelativeLayout implements SwipeTrigger, SwipeRefreshTrigger {

    private ImageView ivRefresh;
    private LinearLayout llRefresh;
    private ImageView ivPen;

    private AnimationDrawable mAnimDrawable;

    private int mHeaderHeight;

    public NoteRefreshHeaderView(Context context) {
        this(context, null);
    }

    public NoteRefreshHeaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NoteRefreshHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mHeaderHeight = context.getResources().getDimensionPixelOffset(R.dimen.refresh_header_height_80);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ivRefresh = findViewById(R.id.ivRefresh);
        llRefresh = findViewById(R.id.llRefresh);
        ivPen = findViewById(R.id.ivPen);
//        mAnimDrawable = (AnimationDrawable) ivRefresh.getBackground();
    }

    @Override
    public void onRefresh() {
        ivPen.setVisibility(VISIBLE);
        llRefresh.setAlpha(1.0f);
        ivPen.setAlpha(1.0f);
//        if (!mAnimDrawable.isRunning()) {
//            mAnimDrawable.start();
//        }
    }

    @Override
    public void onPrepare() {
        ivPen.setVisibility(VISIBLE);
        llRefresh.setAlpha(0.3f);
        ivPen.setAlpha(0.3f);
    }

    @Override
    public void onMove(int y, boolean isComplete, boolean automatic) {
        if (!isComplete) {
            float scale = (float) y / (float) mHeaderHeight;
            if (y >= mHeaderHeight) {
                ivRefresh.setScaleX(1);
                ivRefresh.setScaleY(1);
                llRefresh.setAlpha(1.0f);
                ivPen.setAlpha(1.0f);
                ivPen.setScaleX(1);
                ivPen.setScaleY(1);
            } else if (y > 0) {
                ivRefresh.setScaleX(scale);
                ivRefresh.setScaleY(scale);
                llRefresh.setAlpha(scale);
                ivPen.setAlpha(scale);
                ivPen.setScaleX(scale);
                ivPen.setScaleY(scale);
            } else {
                ivRefresh.setScaleX(0.4f);
                ivRefresh.setScaleY(0.4f);
                ivPen.setScaleX(0.5f);
                ivPen.setScaleY(0.5f);
                llRefresh.setAlpha(0.3f);
                ivPen.setAlpha(0.3f);
            }
        }
    }

    @Override
    public void onRelease() {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onReset() {
        ivPen.setVisibility(VISIBLE);
        llRefresh.setAlpha(1.0f);
        ivPen.setAlpha(1.0f);
    }

    @Override
    public void onCancel() {

    }

}
