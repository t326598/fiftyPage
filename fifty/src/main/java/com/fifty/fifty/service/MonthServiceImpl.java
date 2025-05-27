package com.fifty.fifty.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fifty.fifty.domain.Months;
import com.fifty.fifty.mapper.MonthMapper;

@Service
public class MonthServiceImpl implements MonthService{

    @Autowired
    private MonthMapper monthMapper;

    @Override
    public Months getBackgroundByMonth(int month) {
        return monthMapper.getBackgroundByMonth(month);
    }
    
}
