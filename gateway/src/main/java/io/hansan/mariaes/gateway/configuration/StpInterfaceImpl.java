package io.hansan.mariaes.gateway.configuration;

import cn.dev33.satoken.stp.StpInterface;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：何汉叁
 * @date ：2024/5/19 10:22
 * @description：自定义权限验证接口
 */
@Component
public class StpInterfaceImpl implements StpInterface {

    /**
     * 稍后再从具体业务逻辑中获取权限列表
     */
@Override
public List<String> getPermissionList(Object loginId, String s) {
    List<String> permissions = new ArrayList<String>();
    if ("1".equals(loginId.toString())) {
        permissions.add("question.*");
        permissions.add("page.*");
    }
    return permissions;
}

@Override
public List<String> getRoleList(Object loginId, String s) {
    List<String> roles = new ArrayList<String>();
    if ("1".equals(loginId.toString())) {
        roles.add("admin");
        roles.add("user");
    }
    return roles;
}

}
