package com.colacoco.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.colacoco.common.APIParams.LoginParams;
import com.colacoco.common.Result;
import com.colacoco.entity.TwUser;
import com.colacoco.service.ITwUserService;
import org.springframework.util.Assert;
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
            TwUser user = userService.getOne(new QueryWrapper<TwUser>().eq("user_name",params.getUserName()));
            if(user==null)
                return Result.fail("错误的用户名或密码");

            return Result.succ(user);
        }
}
