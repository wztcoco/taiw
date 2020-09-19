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
 * @since 2020-09-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TwProject implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer projectId;

    private String projectName;

    private LocalDateTime projectTime;

    private Integer typeId;

    private Integer userId;

    private Integer provinceId;

    private Integer hotPoint;

    private Integer deleteStatus;

    private String coverImg;

}
