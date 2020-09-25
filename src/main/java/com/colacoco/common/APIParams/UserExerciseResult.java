package com.colacoco.common.APIParams;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserExerciseResult implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer exerciseId;

    private String exerciseText;

    private String exerciseA;

    private String exerciseB;

    private String exerciseC;

    private String exerciseD;

    private Integer deleteStatus;

    private Integer projectId;

    private Integer exerciseOrder;

    private String exerciseTitle;

    private Integer exerciseScore;

    private Integer exerciseAnswer;

    private Integer userAnswer;
}
