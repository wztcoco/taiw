package com.colacoco.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.colacoco.common.APIParams.UserDetailResult;
import com.colacoco.common.Result;
import com.colacoco.entity.TwUser;
import com.colacoco.service.ITwUserService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author colacoco
 * @since 2020-07-09
 */
@RestController
public class TwUserController {
    @Autowired
    ITwUserService userService;

    @GetMapping("/index")
    public Result index(){
        TwUser user =  userService.getById(1L);
        return Result.succ(200,"操作成功", user);
    }
    @PostMapping("/getUserDetail")
    @ResponseBody
//    public Result getUserDetail(){
//        UserDetailResult userDetailResult = new UserDetailResult();
//        System.out.println(SecurityUtils.getSubject().getSession().getAttribute("userSession"));
//        try{
//            BeanUtils.copyProperties(userDetailResult,SecurityUtils.getSubject().getSession().getAttribute("userSession"));
//            return Result.succ(userDetailResult);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
//        return Result.fail("获取失败");
//    }
    public Result getUserDetail(@RequestBody TwUser userParam){
        UserDetailResult userDetailResult = new UserDetailResult();
        TwUser user =  userService.getOne(new QueryWrapper<TwUser>().eq("user_id",userParam.getUserId()).eq("delete_status",0));
        try{
            BeanUtils.copyProperties(userDetailResult,user);
            return Result.succ(userDetailResult);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return Result.fail("获取失败");
    }
}
