package com.colacoco.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2020-09-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TwExercise implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "exercise_id", type = IdType.AUTO)
    private Integer exerciseId;

    private String exerciseText;

    private Integer exerciseType;

    private Integer deleteStatus;

    private Integer projectId;

    private Integer exerciseOrder;

    private String exerciseTitle;

    private Integer exerciseScore;

    private String exerciseAnswer;


}
