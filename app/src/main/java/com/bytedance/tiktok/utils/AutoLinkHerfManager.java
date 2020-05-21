package com.bytedance.tiktok.utils;

import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.bytedance.tiktok.utils.autolinktextview.AutoLinkMode;
import com.bytedance.tiktok.utils.autolinktextview.AutoLinkTextView;

/**
 * create by libo
 * create on 2020-05-21
 * description
 */
public class AutoLinkHerfManager {
    /**
     * 设置正文内容
     *
     * @param content
     */
    public static void setContent(String content, AutoLinkTextView autoLinkTextView) {

        if (TextUtils.isEmpty(content)) return;

        autoLinkTextView.setVisibility(View.VISIBLE);
        autoLinkTextView.addAutoLinkMode(AutoLinkMode.MODE_HASHTAG, AutoLinkMode.MODE_MENTION, AutoLinkMode.MODE_URL);  //设置需要富文本的模式
        autoLinkTextView.setText(content);
        autoLinkTextView.setAutoLinkOnClickListener((autoLinkMode, matchedText) -> {

            switch (autoLinkMode) {
                case MODE_HASHTAG:
//                    String topicStr = matchedText.substring(1, matchedText.length() - 1);
//                    JumpManager.startTopicDetailActivity(topicStr);
                    break;
                case MODE_MENTION:
//                    String nickName = matchedText.substring(1, matchedText.length() - 1);
//                    checkUserExist(nickName);
                    break;
                case MODE_URL:
//                    JumpManager.startWebActivity(matchedText);
                    break;
            }
        });
    }
}
