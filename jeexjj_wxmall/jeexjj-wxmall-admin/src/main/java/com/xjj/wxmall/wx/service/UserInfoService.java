package com.xjj.wxmall.wx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.xjj.wxmall.customer.buyer.entity.BuyerEntity;
import com.xjj.wxmall.customer.buyer.service.BuyerService;
import com.xjj.wxmall.wx.token.UserInfo;;

@Service
public class UserInfoService {
    @Autowired
    private BuyerService buyerService;


    public UserInfo getInfo(Long userId) {
    	BuyerEntity user = buyerService.getById(userId);
        Assert.state(user != null, "用户不存在");
        UserInfo userInfo = new UserInfo();
        userInfo.setNickName(user.getNickname());
        userInfo.setAvatarUrl(user.getAvatar());
        return userInfo;
    }


}
