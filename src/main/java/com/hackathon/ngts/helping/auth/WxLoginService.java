package com.hackathon.ngts.helping.auth;

import com.hackathon.ngts.helping.config.WeixinConfig;
import com.hackathon.ngts.helping.dao.UserDao;
import com.hackathon.ngts.helping.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by wuzhenjie on 2019-08-29.
 */
@Service
@RequiredArgsConstructor
public class WxLoginService {

    private final WeixinConfig weixinConfig;

    private final RestTemplate restTemplate;

    private final UserDao userDao;

    private final String ACCESS_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";

    private final String USER_INO_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN";

    private WeixinToken getAccessToken(String code) {
        String url = String.format(ACCESS_URL, weixinConfig.getAppId(), weixinConfig.getAppSecret(), code);
        WeixinToken token = restTemplate.getForObject(url, WeixinToken.class);
        return token;
    }

    private WeixinUserInfo getWeixinUserInfo(WeixinToken token) {
        String url = String.format(USER_INO_URL, token.getAccessToken(), token.getOpenid());
        return restTemplate.getForObject(url, WeixinUserInfo.class);
    }

    public boolean login(String code) {
        WeixinToken token = getAccessToken(code);
        WeixinUserInfo weixinUserInfo = getWeixinUserInfo(token);
        Integer id = userDao.countByOpenId(weixinUserInfo.getOpenid());

        HelpingUser helpingUser = new HelpingUser();
        helpingUser.setName(weixinUserInfo.getNickname());
        helpingUser.setAvatar(weixinUserInfo.getHeadimgurl());
        helpingUser.setOpenId(weixinUserInfo.getOpenid());

        if (id != null) {
            helpingUser.setId(id);
        } else {
            User user = new User();
            user.setAvatar(weixinUserInfo.getHeadimgurl());
            user.setName(weixinUserInfo.getNickname());
            user.setWeixin_id(weixinUserInfo.getOpenid());
            userDao.insert(user);
            helpingUser.setId(user.getId());

        }

        Authorization.set(helpingUser);

        return true;
    }


}
