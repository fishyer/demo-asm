package com.ezbuy.asmdemo;

import android.view.View;

import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

/**
 * author : yutianran
 * time   : 2019/01/15
 * desc   :
 * version: 1.0
 */
public class DebouncedClickPredictor {

    public static long DEFAULT_FROZEN_MILLIS = 300L;

    private static final String TAG = DebouncedClickPredictor.class.getSimpleName();

    private static final Map<View, FrozenView> viewWeakHashMap = new WeakHashMap<>();

    /**
     * 是否是快速点击
     *
     * @param targetView 点击的View
     * @return true 直接拦截掉，不处理点击事件
     */
    public static boolean isFastClick(View targetView) {
        FrozenView frozenView = viewWeakHashMap.get(targetView);
        final long now = System.currentTimeMillis();
        //第一次点击，允许执行点击事件
        if (frozenView == null) {
            frozenView = new FrozenView(targetView);
            frozenView.setFrozenWindowTime(now + DEFAULT_FROZEN_MILLIS);
            viewWeakHashMap.put(targetView, frozenView);
            return false;
        }
        //已经超过冻结时间，允许执行点击事件
        if (now >= frozenView.getFrozenWindowTime()) {
            frozenView.setFrozenWindowTime(now + DEFAULT_FROZEN_MILLIS);
            return false;
        }
        //在冻结时间内，禁止执行点击事件
        return true;
    }

    private static class FrozenView extends WeakReference<View> {
        private long frozenWindowTime;

        FrozenView(View referent) {
            super(referent);
        }

        public long getFrozenWindowTime() {
            return frozenWindowTime;
        }

        public void setFrozenWindowTime(long frozenWindowTime) {
            this.frozenWindowTime = frozenWindowTime;
        }
    }
}
