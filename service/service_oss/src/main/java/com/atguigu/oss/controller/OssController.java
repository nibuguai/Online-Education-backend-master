package com.atguigu.oss.controller;

import com.atguigu.commonutils.R;
import com.atguigu.oss.service.OssService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/eduoss/fileoss")
@CrossOrigin
@Slf4j
public class OssController {
    @Autowired
    OssService ossService;

    @PostMapping
    public R uploadOssFile(MultipartFile file) {
        log.info("进入上传文件接口");
        return R.ok().data("url",ossService.uploadFileAvatar(file));
    }
    

    
}
