package com.bytedance.tiktok.fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bytedance.tiktok.R;
import com.bytedance.tiktok.adapter.VideoAdapter;
import com.bytedance.tiktok.base.BaseFragment;
import com.bytedance.tiktok.databinding.FragmentRecommendBinding;

import java.util.ArrayList;

/**
 * create by libo
 * create on 2020-05-19
 * description 推荐播放页
 */
public class RecommendFragment extends BaseFragment {
    private RecyclerView recyclerView;
    private ArrayList<String> datas = new ArrayList<>();
    private VideoAdapter adapter;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected void init() {

        recyclerView = rootView.findViewById(R.id.recyclerview);
        adapter = new VideoAdapter(getActivity(), datas);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        loadData();
    }

    private void loadData() {
        for (int i=0;i<10;i++) {
            datas.add("");
        }
        adapter.notifyDataSetChanged();
    }

}
