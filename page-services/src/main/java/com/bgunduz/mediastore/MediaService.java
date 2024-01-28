package com.bgunduz.mediastore;

import org.springframework.web.multipart.MultipartFile;

public interface MediaService {

    MediaStore addImage(MultipartFile file, String noticeId, String username);
}
