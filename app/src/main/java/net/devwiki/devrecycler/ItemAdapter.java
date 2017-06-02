package net.devwiki.devrecycler;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import net.devwiki.recycler.BaseAdapter;
import net.devwiki.recycler.BaseHolder;

/**
 * Created by zyz on 2017/4/18.
 */

public class ItemAdapter extends BaseAdapter<String, ItemAdapter.ItemHolder> {

    public ItemAdapter(Context context) {
        super(context);
    }

    @Override
    public ItemHolder createCustomViewHolder(ViewGroup parent, int viewType) {
        return new ItemHolder(parent, R.layout.item_text);
    }

    @Override
    public void bindCustomViewHolder(ItemHolder holder, int position) {
        String data = getItem(position);
        holder.mTextView.setText(data);
    }

    @Override
    public int getCustomViewType(int position) {
        return 0;
    }

    public class ItemHolder extends BaseHolder{

        TextView mTextView;

        public ItemHolder(ViewGroup parent, int resId) {
            super(parent, resId);
            mTextView = getView(R.id.text_view);
        }
    }
}
