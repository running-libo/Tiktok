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
        videoBeanOne.setCoverRes(R.mipmap.head1);
        videoBeanOne.setContent("#街坊 #颜值打分 给自己颜值打100分的女生集合");
        videoBeanOne.setVideoRes(R.raw.video1);
        videoBeanOne.setDistance(7.9f);
        videoBeanOne.setFocused(false);
        videoBeanOne.setLiked(true);
        videoBeanOne.setLikeCount("22.5w");
        videoBeanOne.setCommentCount(3480);

        VideoBean.UserBean userBeanOne = new VideoBean.UserBean();
        userBeanOne.setHead(R.mipmap.head1);
        userBeanOne.setNickName("@南京街坊");
        userBeanOne.setSign("你们喜欢的话题，就是我们采访的内容");
        videoBeanOne.setUserBean(userBeanOne);


        VideoBean videoBeanTwo = new VideoBean();
        videoBeanTwo.setCoverRes(R.mipmap.head2);
        videoBeanTwo.setContent("400 户摊主开进济南环联夜市，你们要的烟火气终于来了！");
        videoBeanTwo.setVideoRes(R.raw.video2);
        videoBeanTwo.setDistance(19.7f);
        videoBeanTwo.setFocused(true);
        videoBeanTwo.setLiked(false);
        videoBeanTwo.setLikeCount("108w");
        videoBeanTwo.setCommentCount(8923);

        VideoBean.UserBean userBeanTwo = new VideoBean.UserBean();
        userBeanTwo.setHead( R.mipmap.head2);
        userBeanTwo.setNickName("@民生直通车");
        userBeanTwo.setSign("直通现场新闻，直击社会热点，深入事件背后，探寻事实真相");
        videoBeanTwo.setUserBean(userBeanTwo);


        VideoBean videoBeanThree = new VideoBean();
        videoBeanThree.setCoverRes(R.mipmap.head3);
        videoBeanThree.setContent("科比生涯霸气庆祝动作，最后动作诠释了一生荣耀 #科比 @路人王篮球 ");
        videoBeanThree.setVideoRes(R.raw.video3);
        videoBeanThree.setDistance(15.9f);
        videoBeanThree.setFocused(false);
        videoBeanThree.setLiked(false);
        videoBeanThree.setLikeCount("50.1w");
        videoBeanThree.setCommentCount(9221);

        VideoBean.UserBean userBeanThree = new VideoBean.UserBean();
        userBeanThree.setHead(R.mipmap.head3);
        userBeanThree.setNickName("@七叶篮球");
        userBeanThree.setSign("老科的视频会一直保留，想他了就回来看看");
        videoBeanThree.setUserBean(userBeanThree);


        VideoBean videoBeanFour = new VideoBean();
        videoBeanFour.setCoverRes(R.mipmap.head4);
        videoBeanFour.setContent("美好的一天，从发现美开始 #莉莉柯林斯 ");
        videoBeanFour.setVideoRes(R.raw.video4);
        videoBeanFour.setDistance(25.2f);
        videoBeanFour.setFocused(false);
        videoBeanFour.setLiked(false);
        videoBeanFour.setLikeCount("88w");
        videoBeanFour.setCommentCount(2731);

        VideoBean.UserBean userBeanFour = new VideoBean.UserBean();
        userBeanFour.setHead(R.mipmap.head4);
        userBeanFour.setNickName("@一只爱修图的剪辑师");
        userBeanFour.setSign("接剪辑，活动拍摄，修图单\n 合作私信");
        videoBeanFour.setUserBean(userBeanFour);


        VideoBean videoBeanFive = new VideoBean();
        videoBeanFive.setCoverRes(R.mipmap.head5);
        videoBeanFive.setContent("有梦就去追吧，我说到做到。 #网球  #网球小威 ");
        videoBeanFive.setVideoRes(R.raw.video5);
        videoBeanFive.setDistance(9.2f);
        videoBeanFive.setFocused(false);
        videoBeanFive.setLiked(false);
        videoBeanFive.setLikeCount("13.2w");
        videoBeanFive.setCommentCount(982);

        VideoBean.UserBean userBeanFive = new VideoBean.UserBean();
        userBeanFive.setHead(R.mipmap.head5);
        userBeanFive.setNickName("@国际网球联合会");
        userBeanFive.setSign("ITF国际网球联合会负责制定统一的网球规则，在世界范围内普及网球运动");
        videoBeanFive.setUserBean(userBeanFive);


        VideoBean videoBeanSix = new VideoBean();
        videoBeanSix.setCoverRes(R.mipmap.head6);
        videoBeanSix.setContent("能力越大，责任越大，英雄可能会迟到，但永远不会缺席  #蜘蛛侠 ");
        videoBeanSix.setVideoRes(R.raw.video6);
        videoBeanSix.setDistance(16.4f);
        videoBeanSix.setFocused(true);
        videoBeanSix.setLiked(true);
        videoBeanSix.setLikeCount("210w");
        videoBeanSix.setCommentCount(9723);

        VideoBean.UserBean userBeanSix = new VideoBean.UserBean();
        userBeanSix.setHead(R.mipmap.head6);
        userBeanSix.setNickName("@罗鑫颖");
        userBeanSix.setSign("一个行走在Tr与剪辑之间的人\n 有什么不懂的可以来直播间问我");
        videoBeanSix.setUserBean(userBeanSix);

        VideoBean videoBeanSeven = new VideoBean();
        videoBeanSeven.setCoverRes(R.mipmap.head7);
        videoBeanSeven.setContent("真的拍不出来你的神颜！现场看大屏帅疯！#陈情令南京演唱会 #王一博 😭");
        videoBeanSeven.setVideoRes(R.raw.video7);
        videoBeanSeven.setDistance(16.4f);
        videoBeanSeven.setFocused(false);
        videoBeanSeven.setLiked(false);
        videoBeanSeven.setLikeCount("18.5w");
        videoBeanSeven.setCommentCount(2452);

        VideoBean.UserBean userBeanSeven = new VideoBean.UserBean();
        userBeanSeven.setHead(R.mipmap.head7);
        userBeanSeven.setNickName("@Sean");
        userBeanSeven.setSign("云深不知处");
        videoBeanSeven.setUserBean(userBeanSeven);

        datas.add(videoBeanOne);
        datas.add(videoBeanTwo);
        datas.add(videoBeanThree);
        datas.add(videoBeanFour);
        datas.add(videoBeanFive);
        datas.add(videoBeanSix);
        datas.add(videoBeanSeven);

        datas.add(videoBeanOne);
        datas.add(videoBeanTwo);
        datas.add(videoBeanThree);
        datas.add(videoBeanFour);
        datas.add(videoBeanFive);
        datas.add(videoBeanSix);
        datas.add(videoBeanSeven);

    }
}
