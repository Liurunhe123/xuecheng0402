package com.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xuecheng.base.exception.XueChengPlusException;
import com.xuecheng.content.mapper.CourseTeacherMapper;
import com.xuecheng.content.model.po.CourseTeacher;
import com.xuecheng.content.service.CourseTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Ricky
 * @date: 2023/4/23
 * @projectname: xuecheng0402
 * @description TODO
 **/
@Service
public class CourseTeacherServiceImpl implements CourseTeacherService {

    @Autowired
    CourseTeacherMapper courseTeacherMapper;

    public List<CourseTeacher> getTeacher(Long courseId) {
        LambdaQueryWrapper<CourseTeacher> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper = queryWrapper.eq(CourseTeacher::getCourseId,courseId);
        List<CourseTeacher> courseTeachers = courseTeacherMapper.selectList(queryWrapper);
        return courseTeachers;
    }

    @Override
    public CourseTeacher saveTeacher(CourseTeacher courseTeacher) {
        Long id = courseTeacher.getId();
        int i;
        if (id == null) {
            i = courseTeacherMapper.insert(courseTeacher);
        } else {
            i = courseTeacherMapper.updateById(courseTeacher);
        }
        if (i <= 0) {
            XueChengPlusException.cast("更新失败");
        }
        return courseTeacher;
    }

    @Override
    public void deleteTeacher(Long courseId, Long id) {
        LambdaQueryWrapper<CourseTeacher> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper= queryWrapper.eq(CourseTeacher::getCourseId, courseId).eq(CourseTeacher::getId, id);
        courseTeacherMapper.delete(queryWrapper);
    }

}
