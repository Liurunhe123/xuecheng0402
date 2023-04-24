package com.xuecheng.content.service;

import com.xuecheng.content.model.dto.SaveTeachplanDto;
import com.xuecheng.content.model.dto.TeachplanDto;

import java.util.List;

/**
 * @author: Ricky
 * @date: 2023/4/21
 * @projectname: xuecheng0402
 * @description 课程计划管理相关表
 **/
public interface TeachplanService {

    /**
     * 根据课程id查询课程计划
     * @param courseId 课程计划
     * @return
     */
    public List<TeachplanDto> findTeachPlanTree(Long courseId);

    /**
     * 新增/修改/保存
     * @param saveTeachplanDto 保存内容
     */
    public void saveTeachplan(SaveTeachplanDto saveTeachplanDto);

    /**
     * 删除
     * @param planId 计划id
     */
    public void deleteTeachplan(Long planId);

    /**
     * 课程计划下移
     * @param planId 计划id
     */
    public void moveDownTechplan(Long planId);

    /**
     * 课程计划上移
     * @param planId
     */
    public void moveUpTechplan(Long planId);


}
