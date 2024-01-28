package com.bgunduz.mediastore;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MediaServiceNfsImpl implements MediaService {
    @Value("${bgunduzservices.media.image.uri}")
    private String imageUri;
    @Autowired
    private MediaRepository repository;
    public MediaStore addImage(MultipartFile file, String noticeId, String username) {
        String filePath = imageUri + File.separator + username + File.separator + noticeId + File.separator;

        // Try block to check exceptions
        try {
            File filex = new File(filePath);
            if (!filex.exists()) {
                if (filex.mkdirs()) {
                    System.out.println("Directory is created!");
                } else {
                    System.out.println("Failed to create directory!");
                }
            }
            // Creating an object of FileOutputStream class
            FileOutputStream fout = new FileOutputStream(filePath + file.getOriginalFilename());
            fout.write(file.getBytes());

            // Closing the connection
            fout.close();
            return repository.save(MediaStore.builder().uri(filePath + file.getOriginalFilename()).createdAt(LocalDateTime.now()).username(username).build());
        }

        // Catch block to handle exceptions
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
