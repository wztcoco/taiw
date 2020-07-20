package com.colacoco.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author colacoco
 * @since 2020-07-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TwUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer userId;

    private String userName;

    private String userPhone;

    private String userPsd;

    private Integer userTown;

    private Integer userSex;

    private Integer deleteStatus;

    private String userAvatar;

    private String userEmail;

    private LocalDateTime lastLogin;

    private String userIntro;

    private String userJob;

    private Integer userScore;

    private String userMoney;


}
