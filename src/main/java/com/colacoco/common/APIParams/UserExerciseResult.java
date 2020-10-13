package com.colacoco.common.APIParams;

import com.colacoco.entity.TwAnswer;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UserExerciseResult implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer exerciseId;

    private String exerciseText;

    private Integer exerciseType;

    private String exerciseTypeName;

    private Integer deleteStatus;

    private Integer projectId;

    private Integer exerciseOrder;

    private String exerciseTitle;

    private Integer exerciseScore;

    private String exerciseAnswer;

    private String userAnswer;

    private Integer AnswerNumber;

    private Integer AnswerTrueNumber;

    private List<TwAnswer> answerList;
}
