package com.fifty.fifty.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fifty.fifty.domain.Months;
import com.fifty.fifty.service.MonthServiceImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/months")
@Slf4j
public class MonthController {
    
    @Autowired
    private MonthServiceImpl monthServiceImpl;

    @GetMapping("")
    public ResponseEntity<?> getMethodName(@RequestParam("month") int month) {
       try{
        Months url = monthServiceImpl.getBackgroundByMonth(month);
                return new ResponseEntity<>(url, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
