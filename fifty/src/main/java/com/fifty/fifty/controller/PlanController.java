package com.fifty.fifty.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fifty.fifty.domain.Plan;
import com.fifty.fifty.service.PlanServiceImpl;

import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/plan")
@Slf4j
public class PlanController {

    @Autowired
    private PlanServiceImpl planServiceImpl;

        @GetMapping
        public ResponseEntity<?> getAll() {
            try {
                log.info("이거나오나요??" );
                List<Plan> planList = planServiceImpl.list();
                
                // FullCalendar에 맞게 가공
                List<Map<String, Object>> eventList = planList.stream().map(plan -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("title", plan.getTitle());
                    map.put("start", plan.getStartAt().toString());
                    map.put("end", plan.getEndAt() != null ? plan.getEndAt().toString() : null);
                    map.put("backgroundColor", getCategoryColor(plan.getCrt()));
                    return map;
                }).collect(Collectors.toList());
                
                log.info("나오시나요?" + eventList);
                return new ResponseEntity<>(eventList, HttpStatus.OK);

            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

// 카테고리 색상 매핑
private String getCategoryColor(Long crt) {
    if (crt == null) return "#adb5bd";

    if (crt == 1L) return "#ff6b6b";       // concert
    else if (crt == 2L) return "#4dabf7";  // fanmeeting
    else if (crt == 3L) return "#51cf66";  // promotion
    else return "#adb5bd";                // default
}
    
    @PostMapping()
    public ResponseEntity<?> create(@RequestBody Plan plan) {
        try {
            int result = planServiceImpl.insert(plan);
            if(result > 0){
                return new ResponseEntity<>("OK", HttpStatus.OK);
            }
                return new ResponseEntity<>("FAIL", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping()
    public ResponseEntity<?> update(@RequestBody Plan plan) {
        try {
             int result = planServiceImpl.update(plan);
            if(result > 0){
                return new ResponseEntity<>("OK", HttpStatus.OK);
            }
                return new ResponseEntity<>("FAIL", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/{no}")
    public ResponseEntity<?> destroy(@PathVariable Long no) {
        try {
            int result = planServiceImpl.delete(no);
            if(result > 0){
                return new ResponseEntity<>("OK", HttpStatus.OK);
            }
                return new ResponseEntity<>("FAIL", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
