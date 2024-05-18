package io.hansan.mariaes.auth.controller;

import cn.dev33.satoken.stp.StpUtil;
import io.hansan.mariaes.auth.data.dto.UserSignInDto;
import io.hansan.mariaes.auth.service.AuthorizationValidationService;
import io.hansan.mariaes.common.data.CommonResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：何汉叁
 * @date ：2024/5/18 14:27
 * @description：TODO
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/authorization")
public class BaseAuthController {
    private final AuthorizationValidationService authorizationValidationService;
    @RequestMapping("/signIn")
    public CommonResult signIn(@RequestBody UserSignInDto userSignInDto) {
        Long userId = authorizationValidationService.validateUser(userSignInDto);
        StpUtil.login(userId);
        return CommonResult.success("登录成功");
    }
    @RequestMapping("/signOut")
    public CommonResult signOut() {
        StpUtil.logout();
        return CommonResult.success("登出成功");
    }
}
