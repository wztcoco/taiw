package com.colacoco.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.colacoco.common.Result;
import com.colacoco.entity.VProject;
import com.colacoco.service.IVProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * VIEW 前端控制器
 * </p>
 *
 * @author colacoco
 * @since 2020-09-14
 */
@RestController
public class VProjectController {
    @Autowired
    IVProjectService projectServiceView;

    @PostMapping("/getProjectList")
        @ResponseBody
        public Result getProjectList( ) {
        QueryWrapper<VProject> queryWrapper = new QueryWrapper<VProject>();
        queryWrapper.orderByDesc("hot_point").last("limit 8");;
        List<VProject> projectViewList = projectServiceView.list(queryWrapper);
//        projectViewList.forEach(str->{
//            String s=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(str.getProjectTime());
//            str.setProjectTime(s);
//        });
        if(projectViewList==null)
            return Result.fail("操作失败");
        return Result.succ(projectViewList);
    }
}
