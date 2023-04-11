package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.subject.OneSubject;
import com.atguigu.eduservice.service.IEduSubjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目   分类表   前端控制器
 * </p>
 *
 * @author 王骞
 * @since 2023-02-01
 */
@RestController
@RequestMapping("/subject")
@Slf4j
@CrossOrigin
public class EduSubjectController {
    @Autowired
    private IEduSubjectService IEduSubjectService;

    @PostMapping("/addSubject")
    public R addSubject(MultipartFile file) {
        if (file == null) {
            log.info("上传文件为空");
            return R.error().message("上传文件为空");
        }
        IEduSubjectService.addSubject(file, IEduSubjectService);
        return R.ok();
    }
    @GetMapping("getAllSubject")
    public R getAllSubject(){
        List<OneSubject> list= IEduSubjectService.getOneSubjectList();
        return R.ok().data("list", list);
    }

}

