package io.hansan.mariaes.gateway.configuration;

import cn.dev33.satoken.stp.StpInterface;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author ：何汉叁
 * @date ：2024/5/19 10:22
 * @description：自定义权限验证接口
 */
@Component
public class StpInterfaceImpl implements StpInterface {

    @Override
    public List<String> getPermissionList(Object o, String s) {
        return List.of();
    }

    @Override
    public List<String> getRoleList(Object o, String s) {
        return List.of();
    }
}
