package com.bebas.org.modules.model.base.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.org.bebasWh.core.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * 测试表 Model
 *
 * @author wuhao
 * @date 2022-10-10 17:16:07
 * @tableName sys_test
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_test")
public class SysTestModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名", dataType = "String")
    private String name;

}
