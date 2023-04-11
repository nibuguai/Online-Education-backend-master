package com.atguigu.oss.service;

import com.atguigu.commonutils.R;
import org.springframework.web.multipart.MultipartFile;

public interface OssService {

    String uploadFileAvatar(MultipartFile file);
}
