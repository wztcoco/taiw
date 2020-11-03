package com.colacoco.common.APIParams;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProjectDetailParams implements Serializable{

    private Integer userId;
    private Integer pageSize;
    private Integer exerciseSection;
    private Integer currentPage;
    private Integer projectId;
}
