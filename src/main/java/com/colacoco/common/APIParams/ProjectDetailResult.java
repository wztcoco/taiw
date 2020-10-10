package com.colacoco.common.APIParams;


import com.colacoco.entity.TwExercise;
import com.colacoco.entity.VProject;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ProjectDetailResult implements Serializable {
    private VProject project;
    private List<UserExerciseResult> list;
    private Integer doneNum;
}
