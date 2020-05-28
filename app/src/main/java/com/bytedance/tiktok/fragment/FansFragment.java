package com.bytedance.tiktok.fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bytedance.tiktok.R;
import com.bytedance.tiktok.adapter.FansAdapter;
import com.bytedance.tiktok.base.BaseFragment;
import java.util.ArrayList;

import butterknife.BindView;

public class FansFragment extends BaseFragment {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    private FansAdapter fansAdapter;
    private ArrayList<String> datas = new ArrayList<>();

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_fans;
    }

    @Override
    protected void init() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        fansAdapter = new FansAdapter(getContext(), datas);
        recyclerView.setAdapter(fansAdapter);

        loadData();
    }

    private void loadData() {
        for (int i=0;i<20;i++) {
            datas.add("");
        }
        fansAdapter.notifyDataSetChanged();
    }

}
