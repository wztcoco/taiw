package com.colacoco.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.colacoco.common.Result;
import com.colacoco.entity.TwExercise;
import com.colacoco.entity.TwProject;
import com.colacoco.entity.VProject;
import com.colacoco.service.ITwExerciseService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author colacoco
 * @since 2020-09-21
 */
@RestController
public class TwExerciseController {
    ITwExerciseService exerciseService;
    @PostMapping("/getProjectDetail")
    @ResponseBody
    public Result getProjectDetail(@RequestBody TwProject params) {
        System.out.println(params.getProjectId());
        QueryWrapper<TwExercise> queryWrapper = new QueryWrapper<TwExercise>();
        queryWrapper.orderByDesc("exercise_id");;
        List<TwExercise> exerciseList = exerciseService.list(queryWrapper);
        if(exerciseList==null)
            return Result.fail("操作失败");
        return Result.succ(exerciseList);
    }
}
