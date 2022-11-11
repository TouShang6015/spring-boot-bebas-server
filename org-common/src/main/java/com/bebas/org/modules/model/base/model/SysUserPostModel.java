package com.bebas.org.modules.model.base.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.org.bebasWh.core.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * 用户与岗位关联表 Model
 *
 * @author WuHao
 * @date 2022-05-25 19:02:33
 * @tableName sys_user_post
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("sys_user_post")
public class SysUserPostModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID", dataType = "Long")
    private Long userId;
    /**
     * 岗位ID
     */
    @ApiModelProperty(value = "岗位ID", dataType = "Long")
    private Long postId;

}
