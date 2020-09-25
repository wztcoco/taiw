package com.colacoco.entity;

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
 * @since 2020-09-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class VUserExerciseBind implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer bindId;

    private Integer userId;

    private Integer exerciseId;

    private LocalDateTime bindTime;

    private Integer deleteStatus;

    private Integer bindAnswer;

    private Integer projectId;


}
