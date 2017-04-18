package net.devwiki.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

/**
 * 触摸事件支持类
 * Created by DevWiki on 2016/7/20.
 */

public class ItemTouchSupport {
    private final RecyclerView mRecyclerView;
    private OnItemTouchListener mOnItemTouchListener;
    private View.OnTouchListener mOnTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                RecyclerView.ViewHolder holder = mRecyclerView.getChildViewHolder(v);
                if (mOnItemTouchListener != null) {
                    mOnItemTouchListener.onDown(mRecyclerView, holder.getAdapterPosition(), v);
                }
                return true;
            } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                RecyclerView.ViewHolder holder = mRecyclerView.getChildViewHolder(v);
                if (mOnItemTouchListener != null) {
                    mOnItemTouchListener.onMove(mRecyclerView, holder.getAdapterPosition(), v);
                }
                return true;
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                RecyclerView.ViewHolder holder = mRecyclerView.getChildViewHolder(v);
                if (mOnItemTouchListener != null) {
                    mOnItemTouchListener.onUp(mRecyclerView, holder.getAdapterPosition(), v);
                }
                return true;
            } else if (event.getAction() == MotionEvent.ACTION_CANCEL) {
                RecyclerView.ViewHolder holder = mRecyclerView.getChildViewHolder(v);
                if (mOnItemTouchListener != null) {
                    mOnItemTouchListener.onMoveToOut(mRecyclerView, holder.getAdapterPosition(), v);
                }
                return true;
            }
            return false;
        }
    };

    private RecyclerView.OnChildAttachStateChangeListener mAttachListener
            = new RecyclerView.OnChildAttachStateChangeListener() {
        @Override
        public void onChildViewAttachedToWindow(View view) {
            if (mOnItemTouchListener != null) {
                view.setOnTouchListener(mOnTouchListener);
            }
        }

        @Override
        public void onChildViewDetachedFromWindow(View view) {

        }
    };

    private ItemTouchSupport(RecyclerView recyclerView) {
        mRecyclerView = recyclerView;
        mRecyclerView.setTag(R.id.item_click_support, this);
        mRecyclerView.addOnChildAttachStateChangeListener(mAttachListener);
    }

    public static ItemTouchSupport addTo(RecyclerView view) {
        ItemTouchSupport support = (ItemTouchSupport) view.getTag(R.id.item_click_support);
        if (support == null) {
            support = new ItemTouchSupport(view);
        }
        return support;
    }

    public static ItemTouchSupport removeFrom(RecyclerView view) {
        ItemTouchSupport support = (ItemTouchSupport) view.getTag(R.id.item_click_support);
        if (support != null) {
            support.detach(view);
        }
        return support;
    }

    public ItemTouchSupport setOnItemClickListener(OnItemTouchListener listener) {
        mOnItemTouchListener = listener;
        return this;
    }

    private void detach(RecyclerView view) {
        view.removeOnChildAttachStateChangeListener(mAttachListener);
        view.setTag(R.id.item_click_support, null);
    }

    public interface OnItemTouchListener {

        void onDown(RecyclerView recyclerView, int position, View v);

        void onUp(RecyclerView recyclerView, int position, View v);

        void onMove(RecyclerView recyclerView, int position, View v);

        void onMoveToOut(RecyclerView recyclerView, int position, View v);
    }
}