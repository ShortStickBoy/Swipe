package com.sunzn.swipe.library;

/**
 * Created by sunzn on 2016/5/30.
 */
public interface OnRefreshListener {

    void onPrepare();

    void onRefresh();

    void onReset();

    void onCancel();

}
