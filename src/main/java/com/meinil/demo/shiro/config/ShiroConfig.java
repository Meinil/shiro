package com.meinil.demo.shiro.config;

import com.meinil.demo.shiro.cache.RedisCacheManager;
import com.meinil.demo.shiro.realms.CustomerRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Meinil
 * @Version 1.0
 */
@Configuration
public class ShiroConfig {
    /**
     * ShiroFilter 负责拦截所有请求
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        // 配置系统的受限资源
        Map<String, String> map = new HashMap<>();
        map.put("/register.jsp", "anon");
        map.put("/login.jsp", "anon");
        map.put("/user/**", "anon");
        map.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);

        // 默认认证界面路径
        shiroFilterFactoryBean.setLoginUrl("/login.jsp");
        return shiroFilterFactoryBean;
    }

    /**
     * 安全管理器
     */
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(Realm realm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        return securityManager;
    }

    /**
     * realm
     */
    @Bean
    public Realm getRealm() {
        CustomerRealm realm = new CustomerRealm();
        // 更改验证凭证
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        // 加密算法
        credentialsMatcher.setHashAlgorithmName("MD5");
        // hash次数
        credentialsMatcher.setHashIterations(1024);
        realm.setCredentialsMatcher(credentialsMatcher);

        // 开启缓存
        realm.setCacheManager(new RedisCacheManager());
        realm.setCachingEnabled(true);                  // 全局缓存
        realm.setAuthenticationCachingEnabled(true);    // 认证缓存
        realm.setAuthenticationCacheName("AuthenticationCache");
        realm.setAuthorizationCachingEnabled(true);     // 授权缓存
        realm.setAuthorizationCacheName("AuthorizationCache");

        return realm;
    }
}
