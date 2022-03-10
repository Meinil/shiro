package com.meinil.demo.shiro.realms;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.meinil.demo.entity.Permission;
import com.meinil.demo.entity.Role;
import com.meinil.demo.entity.User;
import com.meinil.demo.service.UserService;
import com.meinil.demo.shiro.salt.MyByteSource;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Author Meinil
 * @Version 1.0
 */
public class CustomerRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String) principals.getPrimaryPrincipal();
        // 角色信息
        List<Role> roles = userService.findRolesByUsername(username);
        if (!CollectionUtils.isEmpty(roles)) {
            SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
            roles.forEach(role -> {
                // 权限信息
                List<Permission> perms = userService.findPermsByRoleId(role.getId());
                if (!CollectionUtils.isEmpty(perms)) {
                    perms.forEach(per -> {
                        authorizationInfo.addStringPermission(per.getName());
                    });
                }

                authorizationInfo.addRole(role.getName());
            });
            return authorizationInfo;
        }

        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        User user = userService.getOne(wrapper);
        if (user != null) {
            return new SimpleAuthenticationInfo(
                    user.getUsername(),
                    user.getPassword(),
                    new MyByteSource(user.getSalt()),
                    this.getName()
            );
        }
        return null;
    }
}
