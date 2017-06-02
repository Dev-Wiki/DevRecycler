package net.devwiki.devrecycler;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private ItemAdapter mItemAdapter;
    private List<String> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh_layout);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        dataList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            dataList.add(String.valueOf(i));
        }
        mItemAdapter = new ItemAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mItemAdapter);
        mItemAdapter.fillList(dataList);
    }
}
