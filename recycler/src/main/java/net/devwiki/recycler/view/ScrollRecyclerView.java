package net.devwiki.recycler.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 滚动回调的RecyclerView
 * Created by zyz on 2017/6/5.
 */

public class ScrollRecyclerView extends RecyclerView {

    private static final String TAG = "ScrollRecyclerView";

    private OnScrollCallback mScrollCallback;
    private boolean isDown = false;
    private boolean isMoved = false;
    private boolean isScrolled = false;
    private int draggingCount = 0;

    public ScrollRecyclerView(Context context) {
        super(context);
        init();
    }

    public ScrollRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ScrollRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        RecyclerView.OnScrollListener scrollListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_SETTLING) {
                    //自己在滚动
                } else if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    //随手指滚动
                    draggingCount = draggingCount + 1;
                } else {
                    //停止滚动
                    if (isScrolled && mScrollCallback != null) {
                        mScrollCallback.onScrollIdle();
                        if (!canScrollVertically(1)) {
                            mScrollCallback.onScrollToBottom();
                        }
                        if (!canScrollVertically(-1)) {
                            mScrollCallback.onScrollToTop();
                        }
                    }
                    isDown = false;
                    isScrolled = false;
                    isMoved = false;
                    draggingCount = 0;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                isScrolled = (dy != 0);
                if (recyclerView.getScrollState() == RecyclerView.SCROLL_STATE_SETTLING) {
                    if (dy > 0) {
                        if (mScrollCallback != null) {
                            mScrollCallback.onSettlingScrollDown();
                        }
                    } else {
                        if (mScrollCallback != null) {
                            mScrollCallback.onSettlingScrollUp();
                        }
                    }
                } else if (recyclerView.getScrollState() == RecyclerView.SCROLL_STATE_DRAGGING) {
                    if (dy > 0) {
                        if (mScrollCallback != null) {
                            mScrollCallback.onDraggingScrollDown();
                        }
                    } else {
                        if (mScrollCallback != null) {
                            mScrollCallback.onDraggingScrollUp();
                        }
                    }
                } else {
                    if (!isDown) {
                        isDown = false;
                        mScrollCallback.onScrollIdle();
                        if (!canScrollVertically(1)) {
                            mScrollCallback.onScrollToBottom();
                        }
                        if (!canScrollVertically(-1)) {
                            mScrollCallback.onScrollToTop();
                        }
                    }
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        };
        addOnScrollListener(scrollListener);
    }

    public void setScrollCallback(@NonNull OnScrollCallback scrollCallback) {
        mScrollCallback = scrollCallback;
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            isDown = true;
        }
        if (e.getAction() == MotionEvent.ACTION_MOVE) {
            isMoved = true;
        }
        if (e.getAction() == MotionEvent.ACTION_UP) {
            if (isMoved && draggingCount == 1) {
                if (!canScrollVertically(1)) {
                    mScrollCallback.onBottomDraggingUp();
                }
                if (!canScrollVertically(-1)) {
                    mScrollCallback.onTopDraggingDown();
                }
            }
        }
        return super.onTouchEvent(e);
    }

    interface OnScrollCallback {

        void onSettlingScrollUp();

        void onSettlingScrollDown();

        void onScrollIdle();

        void onScrollToBottom();

        void onScrollToTop();

        void onDraggingScrollUp();

        void onDraggingScrollDown();

        void onTopDraggingDown();

        void onBottomDraggingUp();
    }
}
