package com.bytedance.tiktok.application;

import androidx.annotation.NonNull;

import java.io.File;

/**
 * create by apple
 * create on 2021/5/28
 * description
 */
class MyExceptionHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {
        File file = dealException(thread, throwable);
        //上传服务器

    }

    /*** 导出异常信息到SD卡 ** @param e */
    private File dealException(Thread thread, Throwable throwable) {
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        File crashFolder = new File(mContext.getExternalCacheDir().getAbsoluteFile(), CrashMonitor.DEFAULT_JAVA_CRASH_FOLDER_NAME);
        if (!crashFolder.exists()) {
            crashFolder.mkdirs();
        }
        File crashFile = new File(crashFolder, time + FILE_NAME_SUFFIX);
        try {
            // 往文件中写入数据
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(crashFile)));
            pw.println(time);
            pw.println(thread);
            pw.println(getPhoneInfo());
            throwable.printStackTrace(pw);  //将异常信息堆栈写入文件
            // 写入crash堆栈
            pw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return crashFile;
    }

    private String getPhoneInfo() {
        PackageManager pm = mContext.getPackageManager();
        PackageInfo pi = null;
        StringBuilder sb = new StringBuilder();

        try {
            pi = pm.getPackageInfo(mContext.getPackageName(), PackageManager.GET_ACTIVITIES);

            // App版本
            sb.append("App Version: ");
            sb.append(pi.versionName);
            sb.append("_");
            sb.append(pi.versionCode + "\n");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        // Android版本号
        sb.append("OS Version: ");
        sb.append(Build.VERSION.RELEASE);
        sb.append("_");
        sb.append(Build.VERSION.SDK_INT + "\n");

        // 手机制造商
        sb.append("Vendor: ");
        sb.append(Build.MANUFACTURER + "\n");

        // 手机型号
        sb.append("Model: ");
        sb.append(Build.MODEL + "\n");

        // CPU架构
        sb.append("CPU: ");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            sb.append(Arrays.toString(Build.SUPPORTED_ABIS));
        } else {
            sb.append(Build.CPU_ABI);
        }
        return sb.toString();
    }
}
