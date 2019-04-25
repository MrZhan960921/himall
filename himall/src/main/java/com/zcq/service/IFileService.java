package com.zcq.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: zcq
 * @Date: 2019/4/25 14:19
 */
public interface IFileService {
    String upload(MultipartFile file, String path);
}
