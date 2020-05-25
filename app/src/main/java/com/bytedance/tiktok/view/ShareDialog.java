package com.bytedance.tiktok.view;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bytedance.tiktok.R;
import com.bytedance.tiktok.adapter.PrivateLetterAdapter;
import com.bytedance.tiktok.adapter.ShareAdapter;
import java.util.ArrayList;

/**
 * create by libo
 * create on 2020-05-25
 * description 分享弹框
 */
public class ShareDialog extends BaseDialog {
    private RecyclerView rvPrivateLetter;
    private RecyclerView rvShare;
    private ArrayList<String> datas = new ArrayList<>();
    private PrivateLetterAdapter privateLetterAdapter;
    private ShareAdapter shareAdapter;

    public ShareDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);

        init();
    }

    private void init() {
        setContentView(R.layout.dialog_share);

        rvPrivateLetter = findViewById(R.id.rv_private_letter);
        rvShare = findViewById(R.id.rv_share);
        rvPrivateLetter.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        privateLetterAdapter = new PrivateLetterAdapter(getContext(), datas);
        rvPrivateLetter.setAdapter(privateLetterAdapter);

        rvShare.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        shareAdapter = new ShareAdapter(getContext(), datas);
        rvShare.setAdapter(shareAdapter);

        show();

        loadPrivateData();
    }

    private void loadPrivateData() {
        for (int i=0;i<8;i++) {
            datas.add("");
        }
        privateLetterAdapter.notifyDataSetChanged();
        shareAdapter.notifyDataSetChanged();
    }
}
