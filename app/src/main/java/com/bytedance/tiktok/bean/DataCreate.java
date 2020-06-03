package com.bytedance.tiktok.bean;

import com.bytedance.tiktok.R;
import java.util.ArrayList;

/**
 * create by libo
 * create on 2020-06-03
 * description 本地数据创建，代替接口获取数据
 */
public class DataCreate {
    public static ArrayList<VideoBean> datas = new ArrayList<>();

    public void initData() {

        VideoBean videoBeanOne = new VideoBean();
        videoBeanOne.setCoverRes(R.mipmap.cover_one);
        videoBeanOne.setContent("只有 #允儿 的脸我才敢拉这么近 @肖战 @王一博 来呀来呀");
        videoBeanOne.setVideoRes(R.raw.video_one);
        videoBeanOne.setDistance(7.9f);
        VideoBean.UserBean userBeanOne = new VideoBean.UserBean();
        userBeanOne.setHead(R.mipmap.head_one);
        userBeanOne.setNickName("一条小团团ovo");
        userBeanOne.setSign("我的座右铭");
        videoBeanOne.setUserBean(userBeanOne);


        VideoBean videoBeanTwo = new VideoBean();
        videoBeanTwo.setCoverRes(R.mipmap.cover_two);
        videoBeanTwo.setContent("只有 #允儿 的脸我才敢拉这么近 @肖战 @王一博 来呀来呀");
        videoBeanTwo.setVideoRes(R.raw.video_two);
        videoBeanTwo.setDistance(19.7f);
        VideoBean.UserBean userBeanTwo = new VideoBean.UserBean();
        userBeanTwo.setHead( R.mipmap.head_two);
        userBeanTwo.setNickName("一条小团团ovo");
        userBeanTwo.setSign("我的座右铭");
        videoBeanTwo.setUserBean(userBeanTwo);


        VideoBean videoBeanThree = new VideoBean();
        videoBeanThree.setCoverRes(R.mipmap.cover_three);
        videoBeanThree.setContent("只有 #允儿 的脸我才敢拉这么近 @肖战 @王一博 来呀来呀");
        videoBeanThree.setVideoRes(R.raw.video_three);
        videoBeanThree.setDistance(15.9f);
        VideoBean.UserBean userBeanThree = new VideoBean.UserBean();
        userBeanThree.setHead(R.mipmap.head_three);
        userBeanThree.setNickName("一条小团团ovo");
        userBeanThree.setSign("我的座右铭");
        videoBeanThree.setUserBean(userBeanThree);


        VideoBean videoBeanFour = new VideoBean();
        videoBeanFour.setCoverRes(R.mipmap.cover_four);
        videoBeanFour.setContent("只有 #允儿 的脸我才敢拉这么近 @肖战 @王一博 来呀来呀");
        videoBeanFour.setVideoRes(R.raw.video_four);
        videoBeanFour.setDistance(25.2f);
        VideoBean.UserBean userBeanFour = new VideoBean.UserBean();
        userBeanFour.setHead(R.mipmap.head_four);
        userBeanFour.setNickName("一条小团团ovo");
        userBeanFour.setSign("我的座右铭");
        videoBeanFour.setUserBean(userBeanFour);


        VideoBean videoBeanFive = new VideoBean();
        videoBeanFive.setCoverRes(R.mipmap.cover_five);
        videoBeanFive.setContent("只有 #允儿 的脸我才敢拉这么近 @肖战 @王一博 来呀来呀");
        videoBeanFive.setVideoRes(R.raw.video_five);
        videoBeanFive.setDistance(9.2f);
        VideoBean.UserBean userBeanFive = new VideoBean.UserBean();
        userBeanFive.setHead(R.mipmap.head_five);
        userBeanFive.setNickName("一条小团团ovo");
        userBeanFive.setSign("我的座右铭");
        videoBeanFive.setUserBean(userBeanFive);


        VideoBean videoBeanSix = new VideoBean();
        videoBeanSix.setCoverRes(R.mipmap.cover_three);
        videoBeanSix.setContent("只有 #允儿 的脸我才敢拉这么近 @肖战 @王一博 来呀来呀");
        videoBeanSix.setVideoRes(R.raw.video_six);
        videoBeanSix.setDistance(16.4f);
        VideoBean.UserBean userBeanSix = new VideoBean.UserBean();
        userBeanSix.setHead(R.mipmap.head_six);
        userBeanSix.setNickName("一条小团团ovo");
        userBeanSix.setSign("我的座右铭");
        videoBeanSix.setUserBean(userBeanSix);

        datas.add(videoBeanOne);
        datas.add(videoBeanTwo);
        datas.add(videoBeanThree);
        datas.add(videoBeanFour);
        datas.add(videoBeanFive);
        datas.add(videoBeanSix);

    }
}
