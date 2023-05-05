package com.xuecheng.content.api;

import com.xuecheng.content.model.dto.BindTeachplanMediaDto;
import com.xuecheng.content.model.dto.SaveTeachplanDto;
import com.xuecheng.content.model.dto.TeachplanDto;
import com.xuecheng.content.service.TeachplanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: Ricky
 * @date: 2023/4/21
 * @projectname: xuecheng0402
 * @description TODO
 **/
@Api(value = "课程计划编辑接口", tags = "课程计划编辑接口")
@RestController
public class TeachPlanController {

    @Autowired
    TeachplanService teachplanService;

    @ApiOperation("查询课程计划属性结构")
    @ApiImplicitParam(value = "courseId", name = "课程id", required = true, dataType = "Long", paramType = "path")
    @GetMapping("/teachplan/{courseId}/tree-nodes")
    public List<TeachplanDto> getTreeNodes(@PathVariable Long courseId) {
        return teachplanService.findTeachPlanTree(courseId);
    }


    @ApiOperation("课程计划创建或修改")
    @PostMapping("/teachplan")
    public void saveTeachplan(@RequestBody SaveTeachplanDto teachplan) {
        teachplanService.saveTeachplan(teachplan);
    }

    @ApiOperation("课程计划删除")
    @DeleteMapping("/teachplan/{planId}")
    public void deleteTeachplan(@PathVariable Long planId) {
        teachplanService.deleteTeachplan(planId);
    }

    @ApiOperation("课程计划下移")
    @PostMapping("/teachplan/movedown/{planId}")
    public void moveDownTechplan(@PathVariable Long planId) {
        teachplanService.moveDownTechplan(planId);
    }

    @ApiOperation("课程计划上移")
    @PostMapping("/teachplan/moveup/{planId}")
    public void moveUpTechplan(@PathVariable Long planId) {
        teachplanService.moveUpTechplan(planId);
    }

    @ApiOperation(value = "课程计划和媒资信息绑定")
    @PostMapping("/teachplan/association/media")
    public void associationMedia(@RequestBody BindTeachplanMediaDto bindTeachplanMediaDto){
        teachplanService.associationMedia(bindTeachplanMediaDto);
    }

}
