package com.fifty.fifty.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.fifty.fifty.domain.Months;

@Mapper
public interface MonthMapper {
     Months getBackgroundByMonth(@Param("month") int month);
}
