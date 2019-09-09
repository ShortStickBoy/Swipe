package com.sunzn.swipe.library.view;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sunzn.swipe.library.R;
import com.sunzn.swipe.library.SwipeRefreshTrigger;
import com.sunzn.swipe.library.SwipeTrigger;

public class TMallRefreshHeaderView extends RelativeLayout implements SwipeTrigger, SwipeRefreshTrigger {

    private static final String TAG = "TMallPullDownHeaderView";

    private ImageView ivRefresh;
    private LinearLayout llRefresh;
    private TextView tvRefresh;

    private AnimationDrawable mAnimDrawable;

    private int mHeaderHeight;

    public TMallRefreshHeaderView(Context context) {
        this(context, null);
    }

    public TMallRefreshHeaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TMallRefreshHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mHeaderHeight = context.getResources().getDimensionPixelOffset(R.dimen.refresh_header_height_100);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ivRefresh = findViewById(R.id.ivRefresh);
        llRefresh = findViewById(R.id.llRefresh);
        tvRefresh = findViewById(R.id.tvRefresh);
        mAnimDrawable = (AnimationDrawable) ivRefresh.getBackground();
        if (!mAnimDrawable.isRunning()) {
            mAnimDrawable.start();
        }
    }

    @Override
    public void onRefresh() {
        tvRefresh.setText("正在刷新...");
        ivRefresh.setBackgroundResource(R.drawable.anim_header_wait_tm);
        mAnimDrawable = (AnimationDrawable) ivRefresh.getBackground();
        if (!mAnimDrawable.isRunning()) {
            mAnimDrawable.start();
        }
    }

    @Override
    public void onPrepare() {
        mAnimDrawable = (AnimationDrawable) ivRefresh.getBackground();
        if (!mAnimDrawable.isRunning()) {
            mAnimDrawable.start();
        }
    }

    @Override
    public void onMove(int y, boolean isComplete, boolean automatic) {
        if (!isComplete) {
            if (y > mHeaderHeight) {
                tvRefresh.setText("释放刷新");
            } else if (y < mHeaderHeight) {
                tvRefresh.setText("下拉刷新");
            }
        }
    }

    @Override
    public void onRelease() {

    }

    @Override
    public void onComplete() {
        mAnimDrawable.stop();
        ivRefresh.setBackgroundResource(R.drawable.anim_header_pull_tm);
        tvRefresh.setText("刷新完成");
    }

    @Override
    public void onReset() {
        mAnimDrawable.stop();
        ivRefresh.setBackgroundResource(R.drawable.anim_header_pull_tm);
        mAnimDrawable = (AnimationDrawable) ivRefresh.getBackground();
        tvRefresh.setText("下拉刷新");
    }

    @Override
    public void onCancel() {

    }

}