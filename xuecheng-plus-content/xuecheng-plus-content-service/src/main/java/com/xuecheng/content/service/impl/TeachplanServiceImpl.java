package com.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xuecheng.base.exception.XueChengPlusException;
import com.xuecheng.content.mapper.TeachplanMapper;
import com.xuecheng.content.mapper.TeachplanMediaMapper;
import com.xuecheng.content.model.dto.SaveTeachplanDto;
import com.xuecheng.content.model.dto.TeachplanDto;
import com.xuecheng.content.model.po.Teachplan;
import com.xuecheng.content.model.po.TeachplanMedia;
import com.xuecheng.content.service.TeachplanService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Ricky
 * @date: 2023/4/21
 * @projectname: xuecheng0402
 * @description TODO
 **/
@Service
public class TeachplanServiceImpl implements TeachplanService {

    @Autowired
    TeachplanMapper teachplanMapper;

    @Autowired
    TeachplanMediaMapper teachplanMediaMapper;

    @Override
    public List<TeachplanDto> findTeachPlanTree(Long courseId) {
        List<TeachplanDto> teachplanDtos = teachplanMapper.selectTreeNodes(courseId);
        return teachplanDtos;
    }

    private int getTeachplanCount(Long parentid,Long courseId) {
        //确定排序字段，找到它的同级结点个数，排序字段就是个数加1
        LambdaQueryWrapper<Teachplan> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper = queryWrapper.eq(Teachplan::getParentid, parentid).eq(Teachplan::getCourseId, courseId);
        Integer count = teachplanMapper.selectCount(queryWrapper);
        return count+1;
    }

    @Override
    public void saveTeachplan(SaveTeachplanDto saveTeachplanDto) {
        Long teachplanId = saveTeachplanDto.getId();
        if (teachplanId == null) {
            // 新增
            Teachplan teachplan = new Teachplan();
            BeanUtils.copyProperties(saveTeachplanDto, teachplan);
            Long parentid = saveTeachplanDto.getParentid();
            Long courseId = saveTeachplanDto.getCourseId();
            //确定排序字段，找到它的同级结点个数，排序字段就是个数加1
            int teachplanCount = getTeachplanCount(parentid, courseId);
            teachplan.setOrderby(teachplanCount);
            teachplanMapper.insert(teachplan);
        } else {
            // 修改
            Teachplan teachplan = teachplanMapper.selectById(teachplanId);
            // 将参数复制到teachplan
            BeanUtils.copyProperties(saveTeachplanDto, teachplan);
            teachplanMapper.updateById(teachplan);
        }
    }

    @Override
    public void deleteTeachplan(Long planId) {
        Teachplan teachplan = teachplanMapper.selectById(planId);
        //查询章节级别
        Integer grade = teachplan.getGrade();
        if (grade == 1) {
            //大章节
            LambdaQueryWrapper<Teachplan> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper = queryWrapper.eq(Teachplan::getParentid, planId);
            Integer count = teachplanMapper.selectCount(queryWrapper);
            //大章节信息还有子集信息
            if (count > 0) {
                XueChengPlusException.cast("课程计划信息还有子级信息，无法操作");
            }
            teachplanMapper.deleteById(planId);
        } else {
            //小章节
            LambdaQueryWrapper<TeachplanMedia> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper = queryWrapper.eq(TeachplanMedia::getTeachplanId, planId);
            TeachplanMedia teachplanMedia = teachplanMediaMapper.selectOne(queryWrapper);
            if (teachplanMedia != null) {
                teachplanMediaMapper.deleteById(teachplanMedia.getId());
            }
            teachplanMapper.deleteById(planId);
        }
    }

    @Override
    public void moveDownTechplan(Long planId) {
        Teachplan teachplan = teachplanMapper.selectById(planId);
        //找到父节点id
        Long parentid = teachplan.getParentid();
        //课程id
        Long courseId = teachplan.getCourseId();
        //当前计划顺序
        Integer orderby = teachplan.getOrderby();
        LambdaQueryWrapper<Teachplan> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper = queryWrapper.eq(Teachplan::getParentid, parentid).eq(Teachplan::getCourseId, courseId).eq(Teachplan::getOrderby, orderby + 1);
        //后一条计划
        Teachplan teachplanNext = teachplanMapper.selectOne(queryWrapper);
        if (teachplanNext == null) {
            return;
        }
        teachplan.setOrderby(orderby + 1);
        teachplanNext.setOrderby(orderby);
        teachplanMapper.updateById(teachplan);
        teachplanMapper.updateById(teachplanNext);
    }

    @Override
    public void moveUpTechplan(Long planId) {
        Teachplan teachplan = teachplanMapper.selectById(planId);
        //当前计划顺序
        Integer orderby = teachplan.getOrderby();
        //父节点
        Long parentid = teachplan.getParentid();
        //课程id
        Long courseId = teachplan.getCourseId();
        LambdaQueryWrapper<Teachplan> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper = queryWrapper.eq(Teachplan::getParentid, parentid).eq(Teachplan::getCourseId, courseId).eq(Teachplan::getOrderby, orderby - 1);
        //前一条计划
        Teachplan teachplanBefore = teachplanMapper.selectOne(queryWrapper);
        if (teachplanBefore == null) {
            return;
        }
        teachplanBefore.setOrderby(orderby);
        teachplan.setOrderby(orderby - 1);
        teachplanMapper.updateById(teachplan);
        teachplanMapper.updateById(teachplanBefore);
    }
}
