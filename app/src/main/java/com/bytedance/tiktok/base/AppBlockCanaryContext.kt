package com.bytedance.tiktok.base

import android.content.Context
import com.github.moduth.blockcanary.BlockCanaryContext
import com.github.moduth.blockcanary.internal.BlockInfo
import java.io.File
import java.util.*

/**
 * create by apple
 * create on 2021/5/28
 * description BlockCanary监测卡顿方案
 */
class AppBlockCanaryContext : BlockCanaryContext() {

    /**
     * Implement in your project.
     *
     * @return Qualifier which can specify this installation, like version + flavor.
     */
    override fun provideQualifier(): String? {
        return "unknown"
    }

    /**
     * Implement in your project.
     *
     * @return user id
     */
    override fun provideUid(): String? {
        return "uid"
    }

    /**
     * Network type
     *
     * @return [String] like 2G, 3G, 4G, wifi, etc.
     */
    override fun provideNetworkType(): String? {
        return "unknown"
    }

    /**
     * Config monitor duration, after this time BlockCanary will stop, use
     * with `BlockCanary`'s isMonitorDurationEnd
     *
     * @return monitor last duration (in hour)
     */
    override fun provideMonitorDuration(): Int {
        return -1
    }

    /**
     * config block threshold (in millis), dispatch over this duration is regarded as a block. you may set it
     * from performance of device.
     *
     * @return threshold in mills
     */
    override fun provideBlockThreshold(): Int {
        return 1000
    }

    /**
     * Thread stack dump interval, use when block happens, BlockCanary will dump on main thread
     * stack according to current sample cycle.
     *
     *
     * Because the implementation mechanism of Looper, real dump interval would be longer than
     * the period specified here (especially when cpu is busier).
     *
     *
     * @return dump interval (in millis)
     */
    override fun provideDumpInterval(): Int {
        return provideBlockThreshold()
    }

    /**
     * Path to save log, like "/blockcanary/", will save to sdcard if can.
     *
     * @return path of log files
     */
    override fun providePath(): String? {
        return "/blockcanary/"
    }

    /**
     * If need notification to notice block.
     *
     * @return true if need, else if not need.
     */
    override fun displayNotification(): Boolean {
        return true
    }

    /**
     * Implement in your project, bundle files into a zip file.
     *
     * @param src  files before compress
     * @param dest files compressed
     * @return true if compression is successful
     */
    override fun zip(src: Array<File?>?, dest: File?): Boolean {
        return false
    }

    /**
     * Implement in your project, bundled log files.
     *
     * @param zippedFile zipped file
     */
    override fun upload(zippedFile: File?) {
        throw UnsupportedOperationException()
    }


    /**
     * Packages that developer concern, by default it uses process name,
     * put high priority one in pre-order.
     *
     * @return null if simply concern only package with process name.
     */
    override fun concernPackages(): List<String?>? {
        return null
    }

    /**
     * Filter stack without any in concern package, used with @{code concernPackages}.
     *
     * @return true if filter, false it not.
     */
    override fun filterNonConcernStack(): Boolean {
        return false
    }

    /**
     * Provide white list, entry in white list will not be shown in ui list.
     *
     * @return return null if you don't need white-list filter.
     */
    override fun provideWhiteList(): List<String?>? {
        val whiteList: LinkedList<String?> = LinkedList()
        whiteList.add("org.chromium")
        return whiteList
    }

    /**
     * Whether to delete files whose stack is in white list, used with white-list.
     *
     * @return true if delete, false it not.
     */
    override fun deleteFilesInWhiteList(): Boolean {
        return true
    }

    /**
     * Block interceptor, developer may provide their own actions.
     */
    override fun onBlock(context: Context?, blockInfo: BlockInfo?) {}
}