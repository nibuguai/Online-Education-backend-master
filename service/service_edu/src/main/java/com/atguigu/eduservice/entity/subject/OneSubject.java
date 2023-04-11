package com.atguigu.eduservice.entity.subject;

import lombok.Data;

import java.util.List;

/**
 * @author 王骞
 * 类说明：
 * @date 2023/2/2 14:26
 */
@Data
public class OneSubject {
    private String id;
    private String title;

    /**
     * 一个一级分类有多个二级分类
     */
    private List<TwoSubject> children;
}