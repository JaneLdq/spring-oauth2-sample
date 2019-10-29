package com.example.oauth.server.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PhotoService {

    private List<PhotoInfo> photos;

    public PhotoService() {
        List<PhotoInfo> photos = new ArrayList<>();
        photos.add(createPhoto("1", "Alice"));
        photos.add(createPhoto("2", "Bob"));
        photos.add(createPhoto("3", "Alice"));
        photos.add(createPhoto("4", "Bob"));
        photos.add(createPhoto("5", "Alice"));
        photos.add(createPhoto("6", "Bob"));
        this.photos = photos;
    }

    private PhotoInfo createPhoto(String id, String userId) {
        PhotoInfo photo = new PhotoInfo();
        photo.setId(id);
        photo.setName("photo" + id + ".jpg");
        photo.setUserId(userId);
        photo.setResourceURL("/com/example/oauth/server/resources/" + photo.getName());
        return photo;
    }

    public InputStream loadPhoto(String id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails details = (UserDetails) authentication.getPrincipal();
            String username = details.getUsername();
            for (PhotoInfo photoInfo : getPhotos()) {
                if (id.equals(photoInfo.getId()) && username.equals(photoInfo.getUserId())) {
                    URL resourceURL = getClass().getResource(photoInfo.getResourceURL());
                    if (resourceURL != null) {
                        try {
                            return resourceURL.openStream();
                        } catch (IOException e) {
                            // fall through...
                        }
                    }
                }
            }
        }
        return null;
    }

    public List<PhotoInfo> getPhotosForCurrentUser(String username) {
        List<PhotoInfo> photoInfos = getPhotos();
        return photoInfos.stream()
                .filter(p -> p.getUserId().equals(username))
                .collect(Collectors.toList());
    }

    public List<PhotoInfo> getPhotos() {
        return photos;
    }

}
