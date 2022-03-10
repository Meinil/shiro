package com.meinil.demo.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Meinil
 * @Version 1.0
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    /**
     * 代码方式
     */
    @GetMapping("orders")
    public String getOrders() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.hasRole("user")) {
            return "请求成功";
        }
        return "无权限";
    }

    /**
     * 注解方式
     * Logical.OR 表示或者
     */
    @GetMapping("save")
//    @RequiresRoles(value = {"admin", "user"}, logical = Logical.OR)
    @RequiresPermissions({"user:update:*"})
    public String save() {
        return "请求成功";
    }
}
