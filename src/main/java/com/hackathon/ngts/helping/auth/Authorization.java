package com.hackathon.ngts.helping.auth;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wuzhenjie on 2019-08-29.
 */
public class Authorization {

    private final static String SESSION_NAME = "login-user-session";


    public static HelpingUser get() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes())
                .getRequest();

        Object o = request.getSession().getAttribute(SESSION_NAME);
        if (o == null) {
            return null;
        }

        return (HelpingUser) o;
    }

    public static void set(HelpingUser user) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes())
                .getRequest();

        request.getSession().setAttribute(SESSION_NAME, user);

    }

}
