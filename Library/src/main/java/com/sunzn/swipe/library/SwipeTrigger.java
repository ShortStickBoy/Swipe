package com.sunzn.swipe.library;

/**
 * Created by sunzn on 2016/5/30.
 */
public interface SwipeTrigger {

    void onPrepare();

    void onMove(int y, boolean isComplete, boolean automatic);

    void onRelease();

    void onComplete();

    void onReset();

    void onCancel();

}
