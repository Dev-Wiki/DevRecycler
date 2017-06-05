package net.devwiki.devrecycler;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.AppCompatTextView;
import android.view.ViewGroup;

import net.devwiki.recycler.BaseAdapter;
import net.devwiki.recycler.BaseHolder;

/**
 * 学生列表的Adapter
 * Created by Asia on 2017/5/7.
 */

public class StudentAdapter extends BaseAdapter<Student, StudentAdapter.StudentHolder> {


    public StudentAdapter(Context context) {
        super(context);
    }

    @Override
    public StudentHolder createCustomViewHolder(ViewGroup parent, int viewType) {
        return new StudentHolder(parent, R.layout.item_student);
    }

    @Override
    public void bindCustomViewHolder(StudentHolder holder, int position) {
        Student student = getItem(position);
        holder.nameTv.setText(student.getName());
        holder.ageTv.setText(String.valueOf(student.getAge()));
    }

    @Override
    public int getCustomViewType(int position) {
        return 0;
    }

    class StudentHolder extends BaseHolder {

        AppCompatTextView nameTv;
        AppCompatTextView ageTv;

        public StudentHolder(ViewGroup parent, @LayoutRes int resId) {
            super(parent, resId);
            nameTv = getView(R.id.student_name_tv);
            ageTv = getView(R.id.student_age_tv);
        }
    }
}
