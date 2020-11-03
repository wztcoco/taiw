package com.colacoco.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.colacoco.common.APIParams.ExerciseDetailParams;
import com.colacoco.common.APIParams.ProjectDetailParams;
import com.colacoco.common.APIParams.ProjectDetailResult;
import com.colacoco.common.APIParams.UserExerciseResult;
import com.colacoco.entity.*;
import com.colacoco.common.Result;
import com.colacoco.service.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    ITwExerciseTypeService exerciseTypeService;
    @Autowired
    ITwAnswerService answerService;
    @Autowired
    IVProjectService projectService;
    @Autowired
    IVUserExerciseBindService userExerciseBindService;
    @PostMapping("/getProjectDetail")
    @ResponseBody
    public Result getProjectDetail(@RequestBody ProjectDetailParams projectDetailParams) {
        Integer projectId = projectDetailParams.getProjectId();
        Integer userId = projectDetailParams.getUserId();
        Integer exerciseSection = projectDetailParams.getExerciseSection();
        //分页
        PageHelper.startPage(projectDetailParams.getCurrentPage(), projectDetailParams.getPageSize());
        List<TwExercise> exerciseList = exerciseService.list(new QueryWrapper<TwExercise>().eq("project_id",projectId).orderByAsc("exercise_order").eq("delete_status",0).eq("exercise_section",exerciseSection));
        VProject project = projectService.getOne(new QueryWrapper<VProject>().eq("project_id",projectId));
        ProjectDetailResult projectDetailResult = new ProjectDetailResult();
        if(project==null)
            return Result.fail("操作失败");
        if(exerciseList==null) {
            projectDetailResult.setProject(project);
            return Result.succ(projectDetailResult);
        }
        List<VUserExerciseBind> ExerciseBindList = userExerciseBindService.list(new QueryWrapper<VUserExerciseBind>().eq("project_id",projectId));
        List<VUserExerciseBind> userAnswerList = ExerciseBindList.stream().filter(item -> item.getUserId() == userId).collect(Collectors.toList());
        Map<Integer,List<VUserExerciseBind>> ExerciseBindMap = ExerciseBindList.stream().collect(Collectors.groupingBy(VUserExerciseBind::getExerciseId));

        List<UserExerciseResult> userExerciseResults = new ArrayList<>();
        for(int i=0;i<exerciseList.size();i++)
        {
            int finali = i;
            UserExerciseResult userExerciseResult = new UserExerciseResult();
            BeanUtil.copyProperties(exerciseList.get(finali),userExerciseResult);
            List<VUserExerciseBind> tempList1 = ExerciseBindMap.get(exerciseList.get(finali).getExerciseId());
            if(tempList1==null) {
                userExerciseResult.setAnswerNumber(0);
                userExerciseResult.setAnswerTrueNumber(0);
                userExerciseResult.setUserAnswer(null);
            }
            else {
                userExerciseResult.setAnswerNumber(tempList1.size());
                List<VUserExerciseBind> AnswerList = tempList1.stream().filter(item -> item.getBindAnswer().equals(exerciseList.get(finali).getExerciseAnswer())).collect(Collectors.toList());
                if(AnswerList==null) {
                    userExerciseResult.setAnswerTrueNumber(0);
                }
                else {
                    userExerciseResult.setAnswerTrueNumber(AnswerList.size());
                }
                List<VUserExerciseBind> userAnswer = tempList1.stream().filter(item -> item.getUserId().equals(userId)).collect(Collectors.toList());
                if(userAnswer==null) {
                    userExerciseResult.setUserAnswer(null);
                }
                else {
                    userExerciseResult.setUserAnswer(userAnswer.get(0).getBindAnswer());
                }
            }
            userExerciseResults.add(userExerciseResult);
        }
        PageInfo<TwExercise> pageInfo = new PageInfo<TwExercise>(exerciseList);
        projectDetailResult.setList(userExerciseResults);
        projectDetailResult.setProject(project);
        projectDetailResult.setDoneNum(userAnswerList.size());
        projectDetailResult.setExerciseNum(pageInfo.getTotal());
        return Result.succ(projectDetailResult);
    }

    @PostMapping("/getExerciseDetail")
    @ResponseBody
    public Result getExerciseDetail(@RequestBody ExerciseDetailParams exerciseDetailParams) {
        UserExerciseResult userExerciseResult = new UserExerciseResult();
        TwExercise exercise = exerciseService.getOne(new QueryWrapper<TwExercise>().eq("exercise_id",exerciseDetailParams.getExerciseId()).eq("delete_status",0));
        VUserExerciseBind userExerciseBind = userExerciseBindService.getOne(new QueryWrapper<VUserExerciseBind>().eq("exercise_id",exerciseDetailParams.getExerciseId()).eq("user_id",exerciseDetailParams.getUserId()));
        List<TwAnswer> answerList =answerService.list(new QueryWrapper<TwAnswer>().eq("exercise_id",exerciseDetailParams.getExerciseId()).eq("delete_status",0));
        TwExerciseType exerciseType = exerciseTypeService.getOne(new QueryWrapper<TwExerciseType>().eq("type_id",exercise.getExerciseType()).eq("delete_status",0));
        BeanUtil.copyProperties(exercise,userExerciseResult);
        if(userExerciseBind!=null)
            userExerciseResult.setUserAnswer(userExerciseBind.getBindAnswer());
        userExerciseResult.setAnswerList(answerList);
        if(exerciseType!=null)
            userExerciseResult.setExerciseTypeName(exerciseType.getTypeName());
        return Result.succ(userExerciseResult);
    }

    @GetMapping("/getExerciseSectionList/{projectId}")
    public Result getExerciseSectionList(@PathVariable String projectId){
        List<TwExercise> exerciseList = exerciseService.list(new QueryWrapper<TwExercise>().select("DISTINCT exercise_section").orderByAsc("exercise_section"));
        return Result.succ(exerciseList);
    }
}
