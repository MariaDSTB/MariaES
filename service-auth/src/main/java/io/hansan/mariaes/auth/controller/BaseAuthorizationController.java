package io.hansan.mariaes.auth.controller;

import cn.dev33.satoken.stp.StpUtil;
import io.hansan.mariaes.auth.data.dto.UserBaseSignInDto;
import io.hansan.mariaes.auth.data.dto.UserBaseSignUpDto;
import io.hansan.mariaes.auth.service.AuthorizationValidationService;
import io.hansan.mariaes.auth.service.BaseRegistrationService;
import io.hansan.mariaes.common.data.CommonResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author ：何汉叁
 * @date ：2024/5/18 14:27
 * @description：TODO
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/authorization")
public class BaseAuthorizationController {
    private final AuthorizationValidationService authorizationValidationService;
    private final BaseRegistrationService baseRegistrationService;
    @PostMapping("/signIn")
    public CommonResult signIn(@RequestBody UserBaseSignInDto userSignInDto) {
        Long userId = authorizationValidationService.validateUser(userSignInDto);
        StpUtil.login(userId);
        return CommonResult.success("登录成功");
    }
    @PostMapping("/signOut")
    public CommonResult signOut() {
        StpUtil.logout();
        return CommonResult.success("登出成功");
    }
    @PostMapping("/signUp")
    public CommonResult signUp(@RequestBody UserBaseSignUpDto userBaseSignUpDto) {
        Long userId = baseRegistrationService.registerUser(userBaseSignUpDto);
        StpUtil.login(userId);
        return CommonResult.success("注册成功");
    }
}
