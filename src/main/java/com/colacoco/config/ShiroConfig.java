package com.colacoco.config;

import com.colacoco.shiro.CustomerRealm;
import com.colacoco.shiro.MyHttpAuthenticationFilter;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    //1. 创建shiroFilter
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean (DefaultWebSecurityManager defaultWebSecurityManager){
        final ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        Map<String, Filter> filter = new HashMap<>();
        filter.put("authc",new MyHttpAuthenticationFilter());
        shiroFilterFactoryBean.setFilters(filter);
        Map<String,String> map = new HashMap<String,String>();
        map.put("/login","anon");
        map.put("/logout","anon");
        map.put("/register","anon");
//        map.put("/**","authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);

        return shiroFilterFactoryBean;
    }
    //2. 创建安全管理器
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManage (Realm realm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(realm);
        return defaultWebSecurityManager;
    }
    //3. 创建自定义realm
    @Bean
    public Realm getRealm(){
        CustomerRealm customerRealm = new CustomerRealm();
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("MD5");
        credentialsMatcher.setHashIterations(1024);
        customerRealm.setCredentialsMatcher(credentialsMatcher);
        return customerRealm;
    }

}
