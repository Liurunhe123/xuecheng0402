package com.xuecheng.content.model.dto;

import com.xuecheng.base.exception.ValidationGroups;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author: Ricky
 * @date: 2023/4/20
 * @projectname: xuecheng0402
 * @description TODO
 **/
@Data
@ApiModel(value="AddCourseDto", description="新增课程基本信息")
public class EditCourseDto extends AddCourseDto {

//    @NotEmpty(message = "修改课程id不能为空", groups = {ValidationGroups.Update.class})
    @ApiModelProperty(value = "课程名称", required = true)
    private Long id;

}
