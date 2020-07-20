package com.colacoco.controller;


import com.colacoco.common.Result;
import com.colacoco.entity.TwUser;
import com.colacoco.service.ITwUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author colacoco
 * @since 2020-07-09
 */
@RestController
@RequestMapping("/tw-user")
public class TwUserController {
    @Autowired
    ITwUserService userService;

    @GetMapping("/index")
    public Result index(){
        TwUser user =  userService.getById(1L);
        return Result.succ(200,"操作成功", user);
    }

}
