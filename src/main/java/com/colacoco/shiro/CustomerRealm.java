package com.colacoco.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.colacoco.common.ApplicationContextUtils;
import com.colacoco.entity.TwUser;
import com.colacoco.service.ITwUserService;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.util.ObjectUtils;

import static org.apache.shiro.util.ByteSource.Util.bytes;

public class CustomerRealm extends AuthorizingRealm {


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("=================================");
        String principal = (String) authenticationToken.getPrincipal();
        ITwUserService userService = (ITwUserService)ApplicationContextUtils.getBean("twUserServiceImpl");
        TwUser user = userService.getOne(new QueryWrapper<TwUser>().eq("user_name",principal));
        if(!ObjectUtils.isEmpty(user)){
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user.getUserName(), user.getUserPsd(), ByteSource.Util.bytes(user.getUserSalt()), this.getName());
            Session session = SecurityUtils.getSubject().getSession();
            session.setAttribute("userSession",user);
            session.setAttribute("userSessionId", user.getUserId());
            return simpleAuthenticationInfo;
        }
        return null;
    }
}
