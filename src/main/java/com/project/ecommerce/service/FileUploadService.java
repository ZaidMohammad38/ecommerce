package com.project.ecommerce.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {

    String uploadImage(MultipartFile file);
}