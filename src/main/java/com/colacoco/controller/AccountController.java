package com.colacoco.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.colacoco.common.APIParams.LoginParams;
import com.colacoco.common.SaltUtils;
import com.colacoco.common.Result;
import com.colacoco.entity.TwUser;
import com.colacoco.service.ITwUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @Autowired
    ITwUserService userService;
    @PostMapping("/login")
        @ResponseBody
        public Result login(@RequestBody LoginParams params){
//            Subject subject = SecurityUtils.getSubject();
//            try{
//                subject.login(new UsernamePasswordToken(params.getUserName(),params.getUserPsd()));
//                return Result.succ("登陆成功");
//            } catch (UnknownAccountException e){
//                e.printStackTrace();
//                System.out.println("用户名错误!");
//            } catch (IncorrectCredentialsException e){
//                e.printStackTrace();
//                System.out.println("密码错误!");
//            }
//            return Result.fail("错误的用户名或密码");

            TwUser user = userService.getOne(new QueryWrapper<TwUser>().eq("user_name",params.getUserName()));
            Md5Hash md5Hash = new Md5Hash(params.getUserPsd(), user.getUserSalt(), 1024);
            if(user==null)
                return Result.fail("错误的用户名或密码");
            if(!user.getUserPsd().equals(md5Hash.toHex()))
                return Result.fail("错误的用户名或密码");
            return Result.succ(user);
        }
    @PostMapping("/logout")
    @ResponseBody
    public Result logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return Result.succ("退出成功");
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
            try{
                userService.save(newUser);
            }catch (Exception e){
                e.printStackTrace();
                return Result.fail("注册失败");
            }
            return Result.succ("注册成功",newUser);
        }
        return Result.fail("用户名已被使用");
    }
}
