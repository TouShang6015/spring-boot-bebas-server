package com.bebas.module.generate.model;

import com.org.bebasWh.core.model.BaseModel;
import lombok.*;

import java.util.List;

/**
 * @author Wuhao
 * @date 2022/9/1 21:32
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TableQueryModel extends BaseModel {

    private String databaseName;

    private String tableComment;

    private String tableName;

    private List<String> tableNames;

}
