package com.jucsinyu.android_mvp_demo.main.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.jucsinyu.android_mvp_demo.R;
import com.jucsinyu.android_mvp_demo.main.presenter.StaggeredHomeAdapter;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity implements MainView {

    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    //    private HomeAdapter mAdapter;
    private StaggeredHomeAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        init();
        initEvent();

    }

    public void init() {
        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);
//        mAdapter = new HomeAdapter(this, mDatas);

        mAdapter = new StaggeredHomeAdapter(this, mDatas);

        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4,
                StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addItemDecoration(new DividerGridItemDecoration(this));
        // 设置item动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

    }

    public void Onclick(View v) {
        onclickChange(v);
    }

    @Override
    public void initEvent() {
        mAdapter.setOnItemClickLitener(new StaggeredHomeAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this, (position + 1) + " click",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(MainActivity.this, (position + 1) + " long click",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onclickChange(View v) {
        switch (v.getId()) {
            case R.id.id_add:
                mAdapter.addData(1);
                break;
            case R.id.id_del:
                mAdapter.removeData(1);
                break;
            case R.id.id_action_gridview:
                mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
                break;
            case R.id.id_action_listview:
                mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                break;
            case R.id.id_action_horizontalGridView:
                mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4,
                        StaggeredGridLayoutManager.HORIZONTAL));
            case R.id.id_action_VERTICALGridView:
                mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4,
                        StaggeredGridLayoutManager.VERTICAL));
                break;
        }
    }

    @Override
    public void initData() {

        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add("" + (char) i);
        }

    }
}
