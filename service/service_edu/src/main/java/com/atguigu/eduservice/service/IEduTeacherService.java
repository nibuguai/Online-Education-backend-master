package com.atguigu.eduservice.service;

import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.entity.vo.TeacherQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-02-24
 */
public interface IEduTeacherService extends IService<EduTeacher> {

    List<EduTeacher> findAll();

    boolean delTeacherById(String id);

    R pageTeacherList(Page<EduTeacher> page);

    

    boolean saveTeacher(EduTeacher eduTeacher);

    R pageTeacherConditions(Long current, Long limit, TeacherQuery teacherQuery);

    R getTeacherById(String id);

    boolean updateTeacher(EduTeacher eduTeacher);
}
