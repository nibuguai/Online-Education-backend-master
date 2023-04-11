package com.atguigu.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.atguigu.eduservice.entity.EduSubject;
import com.atguigu.eduservice.entity.excel.SubjectData;
import com.atguigu.eduservice.service.IEduSubjectService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 王骞
 * 类说明：
 * @date 2023/2/1 14:05
 */
@Slf4j
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {
    private IEduSubjectService IEduSubjectService;


    public SubjectExcelListener(IEduSubjectService IEduSubjectService) {
        this.IEduSubjectService = IEduSubjectService;
    }

    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        log.info(subjectData.toString());
        if (subjectData == null) {
            throw new GuliException(20001, "文件不能为空");
        }
        String oneSubjectName = subjectData.getOneSubjectName();
        String twoSubjectName = subjectData.getTwoSubjectName();
        EduSubject eduSubject = getEduSubject(oneSubjectName, "0");
        if (eduSubject == null) {
            eduSubject=new EduSubject();
            eduSubject.setParentId("0");
            eduSubject.setTitle(oneSubjectName);
            IEduSubjectService.save(eduSubject);

        }
        log.info("=====================================");
        log.info(eduSubject.toString());
        String id = eduSubject.getId();
        EduSubject eduSubjectTwo = getEduSubject(twoSubjectName, id);
        if (eduSubjectTwo == null) {
            eduSubjectTwo=new EduSubject();
            eduSubjectTwo.setTitle(twoSubjectName);
            eduSubjectTwo.setParentId(id);
            IEduSubjectService.save(eduSubjectTwo);
        }

    }


    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
    private EduSubject getEduSubject(String title, String parentId) {
        LambdaQueryWrapper<EduSubject> eduSubjectLambdaQueryWrapper = new LambdaQueryWrapper<>();
        eduSubjectLambdaQueryWrapper.eq(EduSubject::getParentId, parentId).eq(EduSubject::getTitle, title);
        return IEduSubjectService.getOne(eduSubjectLambdaQueryWrapper);
    }

}
