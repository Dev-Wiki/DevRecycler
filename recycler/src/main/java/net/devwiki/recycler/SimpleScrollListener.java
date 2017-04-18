package net.devwiki.recycler;

import android.support.v7.widget.RecyclerView;

/**
 * RecyclerView滚动监听器
 * Created by DevWiki on 2016/7/26.
 */

public class SimpleScrollListener extends RecyclerView.OnScrollListener {

    public interface OnScrollCallback {
        /**
         * 滚动到顶部时回调
         */
        void onScrollToTop();

        /**
         * 向上滚动
         *
         * @param recyclerView 滚动的View
         * @param dy           滚动距离
         */
        void onScrollUp(RecyclerView recyclerView, int dy);

        /**
         * 向下滚动
         *
         * @param recyclerView 滚动的View
         * @param dy           滚动距离
         */
        void onScrollDown(RecyclerView recyclerView, int dy);

        /**
         * 滚动到底部
         */
        void onScrollToBottom();
    }

    private OnScrollCallback callback;

    public SimpleScrollListener(OnScrollCallback callback) {
        this.callback = callback;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            if (!recyclerView.canScrollVertically(1)) {
                if (callback != null) {
                    callback.onScrollToBottom();
                }
            }
            if (!recyclerView.canScrollVertically(-1)) {
                if (callback != null) {
                    callback.onScrollToTop();
                }
            }
        }
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        if (dy > 0) {
            if (callback != null) {
                callback.onScrollDown(recyclerView, dy);
            }
        } else {
            if (callback != null) {
                callback.onScrollUp(recyclerView, dy);
            }
        }
    }
}
