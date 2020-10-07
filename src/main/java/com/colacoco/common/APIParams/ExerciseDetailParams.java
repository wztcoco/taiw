package com.colacoco.common.APIParams;

import lombok.Data;

import java.io.Serializable;

@Data
public class ExerciseDetailParams implements Serializable{

    private Integer userId;
    private Integer exerciseId;
}
