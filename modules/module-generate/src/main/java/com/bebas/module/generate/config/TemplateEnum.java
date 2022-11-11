package com.bebas.module.generate.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wuhao
 * @date 2022/10/9 9:16
 */
@Getter
@AllArgsConstructor
public enum TemplateEnum {

    DTO_JAVA_VM("template/common/model/Dto.java.vm", "{}/model/{}/dto/{}DTO.java"),
    MODEL_JAVA_VM("template/common/model/Model.java.vm", "{}/model/{}/model/{}Model.java"),
    MAPPER_JAVA_VM("template/mapper/Mapper.java.vm", "{}/mapper/{}/{}Mapper.java"),
    MAPPER_XML_VM("template/mapper/Mapper.xml.vm", "{}/mapper/{}/xml/{}Mapper.xml"),
    CONTROLLER_JAVA_VM("template/web/controller/Controller.java.vm", "{}/web/{}/controller/{}Controller.java"),
    SERVICEIMPL_JAVA_VM("template/web/service/impl/ServiceImpl.java.vm", "{}/web/{}/service/impl/{}ServiceImpl.java"),
    SERVICE_JAVA_VM("template/web/service/Service.java.vm", "{}/web/{}/service/I{}Service.java"),
    CONVERT_JAVA_VM("template/common/mapstruct/Convert.java.vm", "{}/convert/{}/{}Convert.java"),
    UI_INDEX_VUE_VM("template/ui/Index.vue.vm", "{}/ui/{}/{}/index.vue"),
    ;

    private final String templateName;

    private final String templatePath;

}
