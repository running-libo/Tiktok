package com.bytedance.tiktok.fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bytedance.tiktok.R;
import com.bytedance.tiktok.adapter.FansAdapter;
import com.bytedance.tiktok.base.BaseFragment;
import com.bytedance.tiktok.bean.DataCreate;
import butterknife.BindView;

public class FansFragment extends BaseFragment {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    private FansAdapter fansAdapter;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_fans;
    }

    @Override
    protected void init() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        fansAdapter = new FansAdapter(getContext(), DataCreate.userList);
        recyclerView.setAdapter(fansAdapter);
    }

}
