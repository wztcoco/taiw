package com.colacoco.common.APIParams;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginParams implements Serializable{

    private String userName;
    private String userPsd;
}
