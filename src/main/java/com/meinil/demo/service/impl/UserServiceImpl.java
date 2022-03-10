package com.meinil.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.meinil.demo.entity.Permission;
import com.meinil.demo.entity.Role;
import com.meinil.demo.entity.User;
import com.meinil.demo.mapper.UserMapper;
import com.meinil.demo.service.UserService;
import com.meinil.demo.utils.SaltUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author Meinil
 * @Version 1.0
 */
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public void register(User user) {
        String salt = SaltUtils.getSalt(255);
        user.setSalt(salt);
        Md5Hash md5Hash = new Md5Hash(user.getPassword(), salt, 1024);
        user.setPassword(md5Hash.toHex());
        baseMapper.insert(user);
    }

    @Override
    public User findRolesByUserId(String username) {
        return baseMapper.findRolesByUserId(username);
    }

    @Override
    public List<Role> findRolesByUsername(String username) {
        return baseMapper.findRolesByUsername(username);
    }

    @Override
    public List<Permission> findPermsByRoleId(String id) {
        return baseMapper.findPermsByRoleId(id);
    }
}
