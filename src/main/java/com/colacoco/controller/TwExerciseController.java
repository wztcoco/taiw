package com.colacoco.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.colacoco.common.APIParams.ProjectDetailParams;
import com.colacoco.common.APIParams.ProjectDetailResult;
import com.colacoco.common.APIParams.UserDetailResult;
import com.colacoco.common.Result;
import com.colacoco.entity.TwExercise;
import com.colacoco.entity.TwUserExerciseBind;
import com.colacoco.entity.VProject;
import com.colacoco.entity.VUserExerciseBind;
import com.colacoco.mapper.TwExerciseMapper;
import com.colacoco.mapper.TwUserExerciseBindMapper;
import com.colacoco.mapper.VProjectMapper;
import com.colacoco.service.ITwExerciseService;
import com.colacoco.service.ITwUserExerciseBindService;
import com.colacoco.service.IVProjectService;
import com.colacoco.service.IVUserExerciseBindService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author colacoco
 * @since 2020-09-22
 */
@RestController
public class TwExerciseController {

    @Autowired
    ITwExerciseService exerciseService;
    @Autowired
    IVProjectService projectService;
    @Autowired
    IVUserExerciseBindService userExerciseBindService;
    @PostMapping("/getProjectDetail")
    @ResponseBody
    public Result getProjectDetail(@RequestBody ProjectDetailParams projectDetailParams) {
        Integer projectId = projectDetailParams.getProjectId();
        System.out.println(projectId);
        QueryWrapper<TwExercise> queryWrapper = new QueryWrapper<TwExercise>();
        queryWrapper.eq("project_id",projectId);
        queryWrapper.orderByAsc("exercise_order");
        PageHelper.startPage(projectDetailParams.getCurrentPage(), projectDetailParams.getPageSize());
        List<TwExercise> exerciseList = exerciseService.list(queryWrapper);
//        PageInfo page = new PageInfo(exerciseList);
        VProject project = projectService.getOne(new QueryWrapper<VProject>().eq("project_id",projectId));
        List<VUserExerciseBind> userExerciseBindList = userExerciseBindService.list(new QueryWrapper<VUserExerciseBind>().eq("project_id",projectDetailParams.getProjectId()).eq("user_id",projectDetailParams.getUserId()));
        if(exerciseList==null||project==null)
            return Result.fail("操作失败");
        ProjectDetailResult projectDetailResult = new ProjectDetailResult();
        projectDetailResult.setList(exerciseList);
        projectDetailResult.setProject(project);
        projectDetailResult.setDoneNum(userExerciseBindList.size());
        return Result.succ(projectDetailResult);
    }

}
