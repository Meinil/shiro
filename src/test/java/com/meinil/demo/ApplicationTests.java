package com.meinil.demo;

import com.meinil.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {
    @Autowired
    private UserService userService;
    @Test
    void contextLoads() {
        userService.findRolesByUserId("zhansan");
    }

}
