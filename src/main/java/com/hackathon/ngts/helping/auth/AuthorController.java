package com.hackathon.ngts.helping.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by wuzhenjie on 2019-08-29.
 */
@RestController
@RequiredArgsConstructor
public class AuthorController {

    private final WxLoginService loginService;

    private final HttpServletResponse response;


    @GetMapping("helping/redirect")
    public void redirect(@RequestParam("redirect_uri") String redirectUri,
                         String code, String state) throws IOException {

        loginService.login(code);
        response.sendRedirect(redirectUri);
    }
}
