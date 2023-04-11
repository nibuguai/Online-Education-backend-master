package com.atguigu.eduservice.service.impl;

import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.entity.vo.TeacherQuery;
import com.atguigu.eduservice.mapper.EduTeacherMapper;
import com.atguigu.eduservice.service.IEduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-02-24
 */
@Service
@Slf4j
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements IEduTeacherService {
    @Override
    public List<EduTeacher> findAll() {
        return super.list(null);
    }

    @Override
    public boolean delTeacherById(String id) {
        return super.removeById(id);
    }

    @Override
    public R pageTeacherList(Page<EduTeacher> page) {
        LambdaQueryWrapper<EduTeacher> dtlqw = new LambdaQueryWrapper<>();
        dtlqw.orderByDesc(EduTeacher::getSort);
        IPage<EduTeacher> page1 = super.page(page, dtlqw);
        /**
         * 总记录数
         */
        long total = page1.getTotal();
        /**
         * 数据list
         */
        List<EduTeacher> records = page1.getRecords();
        return R.ok().data("total", total).data("records", records);
    }

    @Override
    public R pageTeacherConditions(Long current, Long limit, TeacherQuery teacherQuery) {
        log.info("进入讲师分页查询接口");
        LambdaQueryWrapper<EduTeacher> dtlqw = new LambdaQueryWrapper<>();
        dtlqw.like(!StringUtils.isEmpty(teacherQuery.getBegin()), EduTeacher::getGmtCreate, teacherQuery.getBegin())
                .like(!StringUtils.isEmpty(teacherQuery.getEnd()), EduTeacher::getGmtModified, teacherQuery.getEnd())
                .like(!StringUtils.isEmpty(teacherQuery.getLevel()), EduTeacher::getLevel, teacherQuery.getLevel())
                .like(!StringUtils.isEmpty(teacherQuery.getName()), EduTeacher::getName, teacherQuery.getName())
                .orderByDesc(EduTeacher::getSort)
                .orderByDesc(EduTeacher::getGmtCreate);
        Page<EduTeacher> eduTeacherPage = new Page<>(current, limit);
        IPage<EduTeacher> page = this.page(eduTeacherPage, dtlqw);
        List<EduTeacher> records = page.getRecords();
        long total = page.getTotal();
        return R.ok().data("total", total).data("rows", records);
    }

    @Override
    public boolean saveTeacher(EduTeacher eduTeacher) {
        boolean save = this.save(eduTeacher);
        return save;
    }

    @Override
    public R getTeacherById(String id) {
        EduTeacher byId = this.getById(id);

        return R.ok().data("teacher",byId);
    }

    @Override
    public boolean updateTeacher(EduTeacher eduTeacher) {
        LambdaQueryWrapper<EduTeacher> eduTeacherLambdaQueryWrapper = new LambdaQueryWrapper();
        eduTeacherLambdaQueryWrapper.eq(EduTeacher::getId, eduTeacher.getId());
        return  this.update(eduTeacher, eduTeacherLambdaQueryWrapper);
    }
}
