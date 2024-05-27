package io.hansan.mariaes.auth.controller;

/**
 * @author ：何汉叁
 * @date ：2024/5/27 16:55
 * @description：TODO
 */

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.oauth2.config.SaOAuth2Config;
import cn.dev33.satoken.oauth2.logic.SaOAuth2Handle;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Sa-OAuth2 Server端 控制器
 */
@RestController
public class SaOAuth2ServerController {

    // 处理所有OAuth相关请求
    @RequestMapping("/oauth2/*")
    public Object request() {
        System.out.println("------- 进入请求: " + SaHolder.getRequest().getUrl());
        return SaOAuth2Handle.serverRequest();
    }

    // Sa-OAuth2 定制化配置
    @Autowired
    public void setSaOAuth2Config(SaOAuth2Config cfg) {
        cfg.
                // 配置：未登录时返回的View
                setNotLoginView(() -> {
                    String msg = "当前会话在OAuth-Server端尚未登录，请先访问"
                            + "<a href='/oauth2/doLogin?name=sa&pwd=123456' target='_blank'> doLogin登录 </a>"
                            + "进行登录之后，刷新页面开始授权";
                    return msg;
                }).
                // 配置：登录处理函数
                setDoLoginHandle((name, pwd) -> {
                    if("sa".equals(name) && "123456".equals(pwd)) {
                        StpUtil.login(10001);
                        return SaResult.ok();
                    }
                    return SaResult.error("账号名或密码错误");
                }).
                // 配置：确认授权时返回的View
                setConfirmView((clientId, scope) -> {
                    String msg = "<p>应用 " + clientId + " 请求授权：" + scope + "</p>"
                            + "<p>请确认：<a href='/oauth2/doConfirm?client_id=" + clientId + "&scope=" + scope + "' target='_blank'> 确认授权 </a></p>"
                            + "<p>确认之后刷新页面</p>";
                    return msg;
                });
    }

}
