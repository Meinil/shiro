package com.meinil.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.meinil.demo.entity.Permission;
import com.meinil.demo.entity.Role;
import com.meinil.demo.entity.User;

import java.util.List;


/**
 * @Author Meinil
 * @Version 1.0
 */
public interface UserMapper extends BaseMapper<User> {
    /**
     * 根据用户名查询用户的角色
     */
    User findRolesByUserId(String username);
    List<Role> findRolesByUsername(String username);

    /**
     * 根据角色id查询权限
     */
    List<Permission> findPermsByRoleId(String id);
}
