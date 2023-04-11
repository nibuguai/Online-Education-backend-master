package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.entity.vo.TeacherQuery;
import com.atguigu.eduservice.service.IEduTeacherService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-02-24
 */
@Api(tags = "讲师管理")
@RestController
@RequestMapping("/teacher")
@CrossOrigin
@Slf4j
public class EduTeacherController {
    @Autowired
    IEduTeacherService IEduTeacherService;

    /**
     * 查询所有讲师信息
     * @return
     */
    @GetMapping("/findAll")
    public R findAllTeacher() {
        return R.ok().data("items", IEduTeacherService.findAll());
    }

    /**
     * 删除指定讲师
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public  R delTeacherById(@PathVariable String id){
        if (IEduTeacherService.delTeacherById(id)) {
            return R.ok();
        } else {
            return R.error();
        }

    }

    /**
     * 分页查询讲师信息
     * @param current
     * @param limit
     * @return
     */
    @GetMapping("/pageTeacher/{current}/{limit}")
    public R pageTeacherList(@PathVariable Long current, @PathVariable Long limit) {

        Page<EduTeacher> page = new Page<>(current, limit);
        return IEduTeacherService.pageTeacherList(page);

    }

    /**
     * 多条件查询
     * @param current
     * @param limit
     * @param teacherQuery
     * @return
     */
    @PostMapping("/pageTeacherCondition/{current}/{limit}")
    public R pageTeacherConditions(@PathVariable Long current, @PathVariable Long limit, @RequestBody TeacherQuery teacherQuery) {
        return IEduTeacherService.pageTeacherConditions(current,limit,teacherQuery);
    }

    /**
     * 添加讲师接口
     * @param eduTeacher
     * @return
     */
    @PostMapping("/addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher) {
        if (IEduTeacherService.saveTeacher(eduTeacher)){
            return R.ok();
        }else {
           return R.error();
        }
    }

    /**
     * 根据讲师id进行查询
     * @param id
     * @return
     */

    @GetMapping("getTeacher/{id}")
    public R getTeacher(@PathVariable String id) {
        return IEduTeacherService.getTeacherById(id);
    }

    /**
     * 讲师修改功能
     * @param eduTeacher
     * @return
     */
    @PostMapping("updateTeacher")
    public R updateTeacher(@RequestBody EduTeacher eduTeacher) {
        log.info("进入修改接口");

        if(IEduTeacherService.updateTeacher(eduTeacher)) {
            return R.ok();
        } else {
            return R.error();
        }
    }
    @GetMapping
    public R getTest(){
        return R.ok();
    }

}

