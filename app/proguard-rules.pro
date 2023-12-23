# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# 混淆时所采用的算法
-optimizations !code/simplification/arithmetic,!code/simplification/cast,!field/*,!class/merging/*
#指定代码的压缩级别
-optimizationpasses 5
-allowaccessmodification
# 优化 不优化输入的类文件
-dontoptimize
# 预校验
-dontpreverify
# 包名不混合大小写
-dontusemixedcaseclassnames
# 混淆时是否记录日志
-verbose
# 保护注解
-keepattributes *Annotation*
-keepattributes Signature # 避免混淆泛型, 这在JSON实体映射时非常重要
# 忽略警告
-ignorewarnings

-printconfiguration build/intermediates/proguard-files/full-r8-config.txt

-keep public class * extends android.app.Activity
-keep public class * extends androidx.fragment.app.Fragment
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider

-keep class androidx.** {*;}
-keep class android.** {*;}
-keep class kotlin.** {*;}
-keep class kotlinx.** {*;}
-keep class com.android.** {*;}
-keep class com.sun.** {*;}
-keep class org.apache.** {*;}
-keep class org.json.** {*;}
