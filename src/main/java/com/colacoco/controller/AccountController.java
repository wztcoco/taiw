package com.colacoco.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.colacoco.common.APIParams.LoginParams;
import com.colacoco.common.APIParams.SaltUtils;
import com.colacoco.common.Result;
import com.colacoco.entity.TwUser;
import com.colacoco.service.ITwUserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class AccountController {

    @Autowired
    ITwUserService userService;
    @PostMapping("/login")
        @ResponseBody
        public Result login(@RequestBody LoginParams params){
            TwUser user = userService.getOne(new QueryWrapper<TwUser>().eq("user_name",params.getUserName()));
            if(user==null)
                return Result.fail("错误的用户名或密码");
            if(!user.getUserPsd().equals(params.getUserPsd()))
                return Result.fail("错误的用户名或密码");
            return Result.succ(user);
        }
    @PostMapping("/register")
    public Result register(@RequestBody LoginParams params){
        TwUser user = userService.getOne(new QueryWrapper<TwUser>().eq("user_name",params.getUserName()));
        if(user==null) {
            String salt = SaltUtils.getSalt(8);
            Md5Hash md5Hash = new Md5Hash(params.getUserPsd(), salt, 1024);
            TwUser newUser = new TwUser();
            newUser.setUserName(params.getUserName());
            newUser.setUserPsd(md5Hash.toHex());
            newUser.setUserSalt(salt);
            userService.save(newUser);
            return Result.succ("注册成功",newUser);
        }
        return Result.fail("用户名已被使用");
    }
}
