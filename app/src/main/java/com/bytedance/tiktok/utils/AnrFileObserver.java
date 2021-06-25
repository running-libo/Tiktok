package com.bytedance.tiktok.utils;

import android.os.FileObserver;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.File;

/**
 * create by apple
 * create on 2021/5/28
 * description
 */
class AnrFileObserver extends FileObserver {

    public AnrFileObserver(String path) {
        super(path);
    }

    public AnrFileObserver(@NonNull File file) {
        super(file);
    }

    @Override
    public void onEvent(int event, @Nullable String path) {
        switch (event) {
            case FileObserver.ACCESS:

                break;
            case FileObserver.CREATE:

                break;
            case FileObserver.MODIFY:

                break;
        }
    }
}
