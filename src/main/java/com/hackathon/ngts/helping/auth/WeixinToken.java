package com.hackathon.ngts.helping.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by wuzhenjie on 2019-08-29.
 */
@Data
public class WeixinToken {

    @JsonProperty(value = "access_token")
    private String accessToken;

    @JsonProperty(value = "expires_in")
    private int expiresIn;

    @JsonProperty(value = "refresh_token")
    private String refreshToken;

    private String openid;

    private String scope;
}
