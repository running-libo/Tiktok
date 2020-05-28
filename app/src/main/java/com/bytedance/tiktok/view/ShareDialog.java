package com.bytedance.tiktok.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bytedance.tiktok.R;
import com.bytedance.tiktok.adapter.PrivateLetterAdapter;
import com.bytedance.tiktok.adapter.ShareAdapter;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * create by libo
 * create on 2020-05-25
 * description 分享弹框
 */
public class ShareDialog extends BaseBottomSheetDialog {
    @BindView(R.id.rv_private_letter)
    RecyclerView rvPrivateLetter;
    @BindView(R.id.rv_share)
    RecyclerView rvShare;
    private ArrayList<String> datas = new ArrayList<>();
    private PrivateLetterAdapter privateLetterAdapter;
    private ShareAdapter shareAdapter;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dialog_share, container);
        ButterKnife.bind(this, view);
        init();

        return view;
    }

    private void init() {
        rvPrivateLetter.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        privateLetterAdapter = new PrivateLetterAdapter(getContext(), datas);
        rvPrivateLetter.setAdapter(privateLetterAdapter);

        rvShare.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        shareAdapter = new ShareAdapter(getContext(), datas);
        rvShare.setAdapter(shareAdapter);

        loadPrivateData();
    }

    private void loadPrivateData() {
        for (int i=0;i<8;i++) {
            datas.add("");
        }
        privateLetterAdapter.notifyDataSetChanged();
        shareAdapter.notifyDataSetChanged();
    }

    @Override
    protected int getHeight() {
        return dp2px(getContext(), 255);
    }

}
