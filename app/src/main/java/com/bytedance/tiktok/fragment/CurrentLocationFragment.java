package com.bytedance.tiktok.fragment;

import android.os.CountDownTimer;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bytedance.tiktok.R;
import com.bytedance.tiktok.adapter.GridVideoAdapter;
import com.bytedance.tiktok.base.BaseFragment;
import java.util.ArrayList;

import butterknife.BindView;

/**
 * create by libo
 * create on 2020-05-19
 * description 附近的人fragment
 */
public class CurrentLocationFragment extends BaseFragment {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    private GridVideoAdapter adapter;
    private ArrayList<Integer> datas = new ArrayList<>();
    @BindView(R.id.refreshlayout)
    SwipeRefreshLayout refreshLayout;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_current_location;
    }

    @Override
    protected void init() {
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        adapter = new GridVideoAdapter(getActivity(), datas);
        recyclerView.setAdapter(adapter);

        loadData();

        refreshLayout.setColorSchemeResources(R.color.color_link);
        refreshLayout.setOnRefreshListener(() -> new CountDownTimer(1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                refreshLayout.setRefreshing(false);
            }
        }.start());
    }

    private void loadData() {
        int[] picIds = new int[] {R.mipmap.cover_one, R.mipmap.cover_two, R.mipmap.cover_three, R.mipmap.cover_four, R.mipmap.head};
        for (int i=0;i<15;i++) {
            int pos = (int) (Math.random()*5);
            datas.add(picIds[pos]);
        }

        adapter.notifyDataSetChanged();
    }

}
