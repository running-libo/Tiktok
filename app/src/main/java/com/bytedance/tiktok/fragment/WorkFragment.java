package com.bytedance.tiktok.fragment;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bytedance.tiktok.R;
import com.bytedance.tiktok.adapter.WorkAdapter;
import com.bytedance.tiktok.base.BaseFragment;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * create by libo
 * create on 2020-05-19
 * description 个人作品fragment
 */
public class WorkFragment extends BaseFragment {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    private WorkAdapter workAdapter;
    private ArrayList<Integer> datas = new ArrayList<>();

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_work;
    }

    @Override
    protected void init() {
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        workAdapter = new WorkAdapter(getActivity(), datas);
        recyclerView.setAdapter(workAdapter);

        loadData();
    }

    private void loadData() {
        int[] picIds = new int[] {R.mipmap.cover_one, R.mipmap.cover_two, R.mipmap.cover_three, R.mipmap.cover_four, R.mipmap.head};
        for (int i=0;i<25;i++) {
            int pos = (int) (Math.random()*5);
            datas.add(picIds[pos]);
        }
        workAdapter.notifyDataSetChanged();
    }

}
