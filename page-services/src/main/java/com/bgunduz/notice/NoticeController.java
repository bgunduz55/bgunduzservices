package com.bgunduz.notice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/page-services/notice")
public class NoticeController {

    @PostMapping
    public ResponseEntity<Notice> addNotice(){
        return null;
    }
}