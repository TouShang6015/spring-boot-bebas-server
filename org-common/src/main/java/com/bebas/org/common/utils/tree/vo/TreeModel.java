package com.bebas.org.common.utils.tree.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.bebas.org.modules.model.base.dto.BaseMaterialTypeDTO;
import com.bebas.org.modules.model.base.dto.SysDeptDTO;
import com.bebas.org.modules.model.base.dto.SysMenuDTO;
import com.bebas.org.modules.model.base.dto.SysPermissionDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 树结构模型Model
 *
 * @author WuHao
 * @date 2022/5/30 11:15
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TreeModel {

    @TableField(exist = false)
    private Long id;

    @TableField(exist = false)
    private String label;

    @TableField(exist = false)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<TreeModel> children;

    /**
     * 树形结构 构造菜单
     *
     * @param menu
     */
    public TreeModel(SysMenuDTO menu) {
        this.id = menu.getId();
        this.label = menu.getMenuName();
        this.children = Optional.ofNullable(menu.getChildren()).orElseGet(ArrayList::new).stream().map(item -> new TreeModel((SysMenuDTO) item)).collect(Collectors.toList());
    }

    public TreeModel(SysDeptDTO dept) {
        this.id = dept.getId();
        this.label = dept.getDeptName();
        this.children = Optional.ofNullable(dept.getChildren()).orElseGet(ArrayList::new).stream().map(item -> new TreeModel((SysDeptDTO) item)).collect(Collectors.toList());
    }

    public TreeModel(BaseMaterialTypeDTO dto) {
        this.id = dto.getId();
        this.label = dto.getTypeName();
        this.children = Optional.ofNullable(dto.getChildren()).orElseGet(ArrayList::new).stream().map(item -> new TreeModel((BaseMaterialTypeDTO) item)).collect(Collectors.toList());
    }

    public TreeModel(SysPermissionDTO dto) {
        this.id = dto.getId();
        this.label = dto.getTitle();
        this.children = Optional.ofNullable(dto.getChildren()).orElseGet(ArrayList::new).stream().map(item -> new TreeModel((SysPermissionDTO) item)).collect(Collectors.toList());
    }

}
