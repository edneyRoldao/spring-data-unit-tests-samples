package com.edn.base.resizeImage.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    MultipartFile changeFormatAndSize(MultipartFile file);

}
