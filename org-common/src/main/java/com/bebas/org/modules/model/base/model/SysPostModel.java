package com.bebas.org.modules.model.base.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.org.bebasWh.core.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * 岗位信息表 Model
 *
 * @author WuHao
 * @date 2022-05-25 19:02:33
 * @tableName sys_post
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("sys_post")
public class SysPostModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 岗位编码
     */
    @ApiModelProperty(value = "岗位编码", dataType = "String")
    private String postCode;
    /**
     * 岗位名称
     */
    @ApiModelProperty(value = "岗位名称", dataType = "String")
    private String postName;
    /**
     * 显示顺序
     */
    @ApiModelProperty(value = "显示顺序", dataType = "Integer")
    private Integer postSort;
    /**
     * 状态（0正常 1停用）
     */
    @ApiModelProperty(value = "状态（0正常 1停用）", dataType = "String")
    private String status;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", dataType = "String")
    private String remark;

}
