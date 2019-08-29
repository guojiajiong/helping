package com.hackathon.ngts.helping.auth;

import lombok.Data;

/**
 * Created by wuzhenjie on 2019-08-29.
 */
@Data
public class WeixinUserInfo {

    private String openid;

    private String nickname;
    private String sex;
    private String province;

    private String city;

    private String country;

    private String headimgurl;

    private String unionid;
}
