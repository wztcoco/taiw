package com.colacoco.common.APIParams;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
@Data
public class UserDetailResult implements Serializable {

    private Integer userId;

    private String userName;

    private String userPhone;


    private Integer userTown;

    private Integer userSex;

    private String userAvatar;

    private String userEmail;

    private LocalDateTime lastLogin;

    private String userIntro;

    private String userJob;

    private Integer userScore;

    private String userMoney;

    private Integer userRank;
}
