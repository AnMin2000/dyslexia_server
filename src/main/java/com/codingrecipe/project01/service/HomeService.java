package com.codingrecipe.project01.service;

import com.codingrecipe.project01.dto.UserDTO;
import com.codingrecipe.project01.repository.HomeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HomeService {
    private final HomeRepository homeRepository;
    public void insert(UserDTO user){
        System.out.println("id2: " + user.getId() + ", pw: " + user.getPassword());
        homeRepository.insert(user);
    }
}
