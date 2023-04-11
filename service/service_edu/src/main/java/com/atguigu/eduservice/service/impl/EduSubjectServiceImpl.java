package com.atguigu.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.atguigu.eduservice.entity.EduSubject;
import com.atguigu.eduservice.entity.excel.SubjectData;
import com.atguigu.eduservice.entity.subject.OneSubject;
import com.atguigu.eduservice.entity.subject.TwoSubject;
import com.atguigu.eduservice.listener.SubjectExcelListener;
import com.atguigu.eduservice.mapper.EduSubjectMapper;
import com.atguigu.eduservice.service.IEduSubjectService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author 王骞
 * @since 2023-02-01
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements IEduSubjectService {
    @Autowired
    private EduSubjectMapper eduSubjectMapper;
    private InputStream inputStream = null;

    @Override
    public void addSubject(MultipartFile file, IEduSubjectService IEduSubjectService) {
        try {
            inputStream = file.getInputStream();
            EasyExcel.read(inputStream, SubjectData.class, new SubjectExcelListener(IEduSubjectService)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public List<OneSubject> getOneSubjectList() {
        LambdaQueryWrapper<EduSubject> eduSubjectLambdaQueryWrapper = new LambdaQueryWrapper<>();
        eduSubjectLambdaQueryWrapper.eq(EduSubject::getParentId, "0");
        List<OneSubject> oneSubjectList = eduSubjectMapper.selectList(eduSubjectLambdaQueryWrapper).stream().map((item) -> {
                    OneSubject oneSubject = new OneSubject();
                    oneSubject.setId(item.getId());
                    oneSubject.setTitle(item.getTitle());
            LambdaQueryWrapper<EduSubject> twoWrapper = new LambdaQueryWrapper<>();
            twoWrapper.eq(EduSubject::getParentId, item.getId());
            List<TwoSubject> twoSubjectList = eduSubjectMapper.selectList(twoWrapper).stream().map((item2) -> {
                TwoSubject twoSubject = new TwoSubject();
                twoSubject.setId(item2.getId());
                twoSubject.setTitle(item2.getTitle());
                return twoSubject;
            }).collect(Collectors.toList());
            oneSubject.setChildren(twoSubjectList);
            return oneSubject;
                }
        ).collect(Collectors.toList());
        return oneSubjectList;
    }
}
