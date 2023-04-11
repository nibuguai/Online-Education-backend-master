package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.EduCourseDescription;
import com.atguigu.eduservice.entity.vo.CourseInfoVo;
import com.atguigu.eduservice.mapper.EduCourseMapper;
import com.atguigu.eduservice.service.IEduCourseDescriptionService;
import com.atguigu.eduservice.service.IEduCourseService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mysql.cj.log.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.rmi.CORBA.Util;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author 王骞
 * @since 2023-02-02
 */
@Service
@Slf4j
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements IEduCourseService {
    @Autowired
    private IEduCourseDescriptionService eduCourseDesciptionService;
    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        log.info("courseInfoVo==="+courseInfoVo);
        //1、向课程表添加基本信息
        EduCourse eduCourse = new EduCourse();
       BeanUtils.copyProperties(courseInfoVo, eduCourse);
        int insert = baseMapper.insert(eduCourse);
        if (insert==0) {
            throw new GuliException(20001, "添加课程失败");
        }
        //2、向课程简介表添加基本信息
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        BeanUtils.copyProperties(courseInfoVo, eduCourseDescription);
        /**
         * 获得课程id,课程表和简介表示一对一的关系，所以id得相同
         */
        String id = eduCourse.getId();
        eduCourseDescription.setId(id);
        eduCourseDesciptionService.save(eduCourseDescription);
        return id;
    }
}
