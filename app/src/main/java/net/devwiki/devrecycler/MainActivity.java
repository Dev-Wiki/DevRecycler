package net.devwiki.devrecycler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView studentRv;
    private StudentAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        studentRv = (RecyclerView) findViewById(R.id.student_rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        studentRv.setLayoutManager(layoutManager);
        mAdapter = new StudentAdapter(this);
        View headerView = LayoutInflater.from(this).inflate(R.layout.header_student, studentRv, false);
        mAdapter.addHeaderView(headerView);
        studentRv.setAdapter(mAdapter);
        List<Student> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Student student = new Student();
            student.setName("学生" + i);
            student.setAge(i);
            list.add(student);
        }
        mAdapter.fillList(list);
    }
}
