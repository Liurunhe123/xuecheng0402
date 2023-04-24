package com.xuecheng.content.service;

import com.xuecheng.content.model.po.CourseTeacher;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author: Ricky
 * @date: 2023/4/23
 * @projectname: xuecheng0402
 * @description 课程教师
 **/
public interface CourseTeacherService {

    /**
     * 根据id查询教师
     * @param id
     * @return
     */
    public List<CourseTeacher> getTeacher(Long id);

    public CourseTeacher saveTeacher(CourseTeacher courseTeacher);

    public void deleteTeacher(Long courseId, Long id);

}
