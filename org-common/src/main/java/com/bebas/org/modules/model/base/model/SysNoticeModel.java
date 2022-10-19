package com.bebas.org.modules.model.base.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import com.org.bebasWh.core.model.BaseModel;
import lombok.*;

/**
 * 通知公告表 Model
 *
 * @author WuHao
 * @date 2022-05-25 19:02:33
 * @tableName sys_notice
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("sys_notice")
public class SysNoticeModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/**
	 * 公告标题
	 */
	@ApiModelProperty(value = "公告标题", dataType = "String")
	private String noticeTitle;
	/**
	 * 公告类型（1通知 2公告）
	 */
	@ApiModelProperty(value = "公告类型（1通知 2公告）", dataType = "String")
	private String noticeType;
	/**
	 * 公告内容
	 */
	@ApiModelProperty(value = "公告内容", dataType = "String")
	private String noticeContent;
	/**
	 * 公告状态（0正常 1关闭）
	 */
	@ApiModelProperty(value = "公告状态（0正常 1关闭）", dataType = "String")
	private String status;

}
