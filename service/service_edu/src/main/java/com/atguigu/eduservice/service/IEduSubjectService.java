package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.EduSubject;
import com.atguigu.eduservice.entity.subject.OneSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author 王骞
 * @since 2023-02-01
 */
public interface IEduSubjectService extends IService<EduSubject> {

    void addSubject(MultipartFile file, IEduSubjectService IEduSubjectService);

    List<OneSubject> getOneSubjectList();
}
