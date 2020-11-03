package com.colacoco.entity;

import java.sql.Date;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * VIEW
 * </p>
 *
 * @author colacoco
 * @since 2020-09-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class VProject implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer projectId;

    private String projectName;

    private Date projectTime;

    private Integer typeId;

    private Integer userId;

    private Integer provinceId;

    private Integer hotPoint;

    private String userName;

    private String typeName;

    private String coverImg;

    private Long projectNum;

    private String userJob;

    private String userPhone;
}
