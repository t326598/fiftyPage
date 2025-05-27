package com.fifty.fifty.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fifty.fifty.domain.Profile;
import com.fifty.fifty.service.ProfileServiceImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/profile")
@Slf4j
public class ProfileController {

    @Autowired
    private ProfileServiceImpl profileServiceImpl;

    @GetMapping
    public ResponseEntity getMethodName() throws Exception {
        List<Profile> list = profileServiceImpl.list();

        Map<String, Object> result = new HashMap<>();
        result.put("list", list);

        return ResponseEntity.ok(result);

        
    }
    

    
}
