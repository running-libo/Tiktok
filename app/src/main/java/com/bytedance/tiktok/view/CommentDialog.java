package com.bytedance.tiktok.view;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bytedance.tiktok.R;
import com.bytedance.tiktok.adapter.CommentAdapter;

import java.util.ArrayList;

/**
 * create by libo
 * create on 2020-05-24
 * description 评论弹框
 */
public class CommentDialog extends BaseDialog {
    private RecyclerView recyclerView;
    private CommentAdapter commentAdapter;
    private ArrayList<String> datas = new ArrayList<>();

    public CommentDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);

        init();
    }

    private void init() {
        setContentView(R.layout.dialog_comment);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        commentAdapter = new CommentAdapter(getContext(), datas);
        recyclerView.setAdapter(commentAdapter);

        show();

        loadData();
    }

    private void loadData() {
        for (int i=0;i<15;i++) {
            datas.add("");
        }
        commentAdapter.notifyDataSetChanged();
    }
}
