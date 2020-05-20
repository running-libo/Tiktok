package com.bytedance.tiktok.fragment;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.bytedance.tiktok.R;
import com.bytedance.tiktok.adapter.GridVideoAdapter;
import com.bytedance.tiktok.base.BaseFragment;
import java.util.ArrayList;

public class CurrentLocationFragment extends BaseFragment {
    private RecyclerView recyclerView;
    private GridVideoAdapter adapter;
    private ArrayList<Integer> datas = new ArrayList<>();

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_current_location;
    }

    @Override
    protected void init() {
        recyclerView = rootView.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        adapter = new GridVideoAdapter(getActivity(), datas);
        recyclerView.setAdapter(adapter);

        loadData();
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
