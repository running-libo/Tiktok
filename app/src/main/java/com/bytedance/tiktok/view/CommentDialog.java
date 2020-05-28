package com.bytedance.tiktok.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bytedance.tiktok.R;
import com.bytedance.tiktok.adapter.CommentAdapter;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * create by libo
 * create on 2020-05-24
 * description 评论弹框
 */
public class CommentDialog extends BaseBottomSheetDialog {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    private CommentAdapter commentAdapter;
    private ArrayList<String> datas = new ArrayList<>();
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dialog_comment, container);
        ButterKnife.bind(this, view);

        init();

        return view;
    }

    private void init() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        commentAdapter = new CommentAdapter(getContext(), datas);
        recyclerView.setAdapter(commentAdapter);

        loadData();
    }

    private void loadData() {
        for (int i=0;i<15;i++) {
            datas.add("");
        }
        commentAdapter.notifyDataSetChanged();
    }

    @Override
    protected int getHeight() {
        return getResources().getDisplayMetrics().heightPixels - 600;
    }
}
