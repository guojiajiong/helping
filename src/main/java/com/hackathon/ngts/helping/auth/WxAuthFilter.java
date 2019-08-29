package com.hackathon.ngts.helping.auth;

import com.hackathon.ngts.helping.config.Constant;
import com.hackathon.ngts.helping.config.WeixinConfig;
import lombok.RequiredArgsConstructor;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * Created by wuzhenjie on 2019-08-29.
 */
@RequiredArgsConstructor
public class WxAuthFilter implements Filter {

    private final static String AUTHORIZE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";


    private final WeixinConfig weixinConfig;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        if (Authorization.get() == null) {
            HttpServletRequest request = (HttpServletRequest) servletRequest;


            StringBuilder sb = new StringBuilder();
            sb.append(Constant.baseUrl).append(request.getRequestURI());
            if (request.getQueryString() != null) {
                sb.append(request.getQueryString());
            }

            String realUrl = sb.toString();

            realUrl = URLEncoder.encode(realUrl, "UTF-8");

            String redirectUri = Constant.baseUrl + "/helping/redirect?redirect_uri="
                    + realUrl;

            redirectUri = URLEncoder.encode(redirectUri, "UTF-8");

            String url = String.format(AUTHORIZE_URL, weixinConfig.getAppId(), redirectUri);

            HttpServletResponse response = (HttpServletResponse) servletResponse;

            response.sendRedirect(url);

            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);

    }
}
