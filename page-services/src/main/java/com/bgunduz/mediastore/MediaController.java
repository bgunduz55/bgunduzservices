package com.bgunduz.mediastore;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.URI;
import java.security.Principal;

@RestController
@RequestMapping("/api/v1/page-services/media")
@Slf4j
public class MediaController {
    @Value("${bgunduzservices.media.image.uri}")
    private String imageUri;
    @Autowired
    private MediaServiceNfsImpl mediaService;
    @PostMapping
    public ResponseEntity<MediaStore> addMediaStore(
            @RequestParam(value = "file") MultipartFile file,
            @RequestParam(value="noticeId") String noticeId, Principal customUser
    ) {
        final Jwt jwt = (Jwt) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();

        String username = jwt.getClaim("preferred_username");
        String filePath = imageUri + File.separator + username + File.separator + noticeId + File.separator;
        log.debug("User Name: " + jwt.getClaim("email"));
//        return ResponseEntity.ok(MediaStore.builder().uri(jwt.getClaim("email")).build());
        URI asd =  URI.create(filePath);
        return ResponseEntity.created(URI.create(filePath))
                .body(mediaService.addImage(file, noticeId, username));
    }
}
