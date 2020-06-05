# Tiktok
高仿抖音APP


在App中使用地图定位十分常见，购物功能的可以直接定位当前位置，发动态功能可以定位当前位置发出，社交功能可以定位周边用户等等。这里我使用高德地图定位当前位置并显示地址和经纬度。

[github代码传送门](https://github.com/18380438200/GaodeMap)

先上效果图：
![](https://upload-images.jianshu.io/upload_images/8669504-0339414b2596aa72.gif?imageMogr2/auto-orient/strip)


##实现步骤：

#####1. 创建应用获取key：
接入第三方无一例外，去高德地图官网注册账号并创建应用。填入包名和keystore的Sha1。

![创建应用](http://upload-images.jianshu.io/upload_images/8669504-773695e59b58fe18.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/800)

#####获取Sha1，输入命令keytool -v -list -keystore  keystore文件路径
- ######获取调试版Sha1：
进入.android目录
```
cd .android
```

输入命令获取
```
keytool -v -list -keystore debug.keystore
```
过程截图：
![image.png](http://upload-images.jianshu.io/upload_images/8669504-a2189ac6e2278262.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/800)

- ######获取正式版Sha1：
输入命令获取接正式keystore路径
```
keytool -v -list -keystore ../../xxx.keystore
```

过程截图：
  ![image.png](http://upload-images.jianshu.io/upload_images/8669504-9bc19d98f177d694.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/800)


#####创建完成获取Key：
![image.png](http://upload-images.jianshu.io/upload_images/8669504-24753d2e5071857f.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
