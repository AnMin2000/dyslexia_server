package com.codingrecipe.project01.service;

import com.codingrecipe.project01.dto.Camera;
import com.codingrecipe.project01.dto.User;
import com.codingrecipe.project01.repository.HomeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HomeService {
    private final HomeRepository homeRepository;
    public void insert(User user){
        System.out.println("id2: " + user.getId() + ", pw: " + user.getPassword());
        homeRepository.insert(user);
    }
    public void shot(Camera camera){
        System.out.println("camera : " + camera.getAlbumId());

    }

}
