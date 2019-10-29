package com.example.oauth.server.controller;

import com.example.oauth.server.service.PhotoInfo;
import com.example.oauth.server.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.List;

@Controller
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    @PreAuthorize("#oauth2.hasScope('read')")
    @RequestMapping("/api/photos/{photoId}")
    public ResponseEntity<byte[]> getPhoto(@PathVariable("photoId") String id) throws IOException {
        InputStream photo = photoService.loadPhoto(id);
        if (photo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = photo.read(buffer);
            while (len >= 0) {
                out.write(buffer, 0, len);
                len = photo.read(buffer);
            }
            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.CONTENT_TYPE, "image/jpeg");
            return new ResponseEntity<>(out.toByteArray(), headers, HttpStatus.OK);
        }
    }

    @PreAuthorize("#oauth2.hasScope('read')")
    @RequestMapping(value = "/api/photos")
    public ResponseEntity<List<PhotoInfo>> getPhotos(Principal principal) {
        List<PhotoInfo> photos = photoService.getPhotosForCurrentUser(principal.getName());
        return ResponseEntity.ok(photos);
    }
}
