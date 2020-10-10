package com.colacoco.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2020-09-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TwUserExerciseBind implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "bind_id", type = IdType.AUTO)
    private Integer bindId;

    private Integer userId;

    private Integer exerciseId;

    private LocalDateTime bindTime;

    private Integer deleteStatus;

    private String bindAnswer;


}
