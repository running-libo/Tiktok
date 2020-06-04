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
import com.bytedance.tiktok.bean.CommentBean;
import com.bytedance.tiktok.bean.DataCreate;
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
    private ArrayList<CommentBean> datas = new ArrayList<>();
    private View view;
    private int[] likeArray = new int[] {4919, 334,121,423,221,23};
    private String[] commentArray = new String[] {"我就说左脚踩右脚可以上天你们还不信！", "全是评论点赞，没人关注吗", "哈哈哈哈", "像谁，没看出来", "你这西安话真好听"};

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
        for (int i = 0; i < DataCreate.userList.size(); i++) {
            CommentBean commentBean = new CommentBean();
            commentBean.setUserBean(DataCreate.userList.get(i));
            commentBean.setContent(commentArray[(int) (Math.random()*commentArray.length)]);
            commentBean.setLikeCount(likeArray[(int) (Math.random()*likeArray.length)]);
            datas.add(commentBean);
        }
        commentAdapter.notifyDataSetChanged();
    }

    @Override
    protected int getHeight() {
        return getResources().getDisplayMetrics().heightPixels - 600;
    }
}
