package com.xuecheng.content.api;

import com.xuecheng.content.mapper.CourseTeacherMapper;
import com.xuecheng.content.model.po.CourseTeacher;
import com.xuecheng.content.service.CourseTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: Ricky
 * @date: 2023/4/23
 * @projectname: xuecheng0402
 * @description TODO
 **/
@RestController
public class CourseTeacherController {

    @Autowired
    CourseTeacherService courseTeacherService;

    @ApiOperation("查询教师")
    @GetMapping("/courseTeacher/list/{courseId}")
    public List<CourseTeacher> getTeacher(@PathVariable Long courseId) {
        return courseTeacherService.getTeacher(courseId);
    }

    @ApiOperation("添加或修改教师")
    @PostMapping("/courseTeacher")
    public CourseTeacher saveTeacher(@RequestBody CourseTeacher courseTeacher) {
        return courseTeacherService.saveTeacher(courseTeacher);
    }

    @ApiOperation("删除教师")
    @DeleteMapping("/courseTeacher/course/{courseId}/{id}")
    public void deleteTeacher(@PathVariable Long courseId, @PathVariable Long id) {
        courseTeacherService.deleteTeacher(courseId,id);
    }


}
