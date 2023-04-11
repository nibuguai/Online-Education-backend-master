package com.atguigu.eduservice.controller;


import com.alibaba.fastjson2.JSON;
import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.vo.CourseInfoVo;
import com.atguigu.eduservice.service.IEduCourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

/**
 * <p>
 * 课程表: 存储课程基本信息  前端控制器
 * </p>
 *
 * @author 王骞
 * @since 2023-02-02
 */
@RestController
@RequestMapping("/course")
@CrossOrigin
@Slf4j
public class EduCourseController {
    @Autowired
    private IEduCourseService eduCourseService;

    @PostMapping("/addCourseInfo")
    public R saveCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        log.info("======================================");
        log.info(String.valueOf(courseInfoVo));
        /**
         * 添加成功后需要返回课程id，为后面添加大纲使用
         */
        String id = eduCourseService.saveCourseInfo(courseInfoVo);

        return R.ok().data("courseId", id);
    }
}

