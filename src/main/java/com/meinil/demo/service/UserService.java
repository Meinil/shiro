package com.meinil.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.meinil.demo.entity.Permission;
import com.meinil.demo.entity.Role;
import com.meinil.demo.entity.User;

import java.util.List;

/**
 * @Author Meinil
 * @Version 1.0
 */
public interface UserService extends IService<User> {
    public void register(User user);

    /**
     * 根据用户名查询用户的角色
     */
    User findRolesByUserId(String username);
    List<Role> findRolesByUsername(String username);

    /**
     * 根据角色id查询对应的权限信息
     */
    List<Permission> findPermsByRoleId(String id);
}
