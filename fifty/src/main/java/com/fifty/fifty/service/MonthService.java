package com.fifty.fifty.service;

import org.apache.ibatis.annotations.Param;

import com.fifty.fifty.domain.Months;

public interface MonthService {
    Months getBackgroundByMonth(@Param("month") int month);
}
