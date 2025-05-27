package com.fifty.fifty.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.fifty.fifty.domain.YoutubeChartData;

@Mapper
public interface YoutubeChartMapper {
     YoutubeChartData findByVideoId(String videoId);
    void insertChart(YoutubeChartData dto);
    void updateChart(YoutubeChartData dto);
    List<YoutubeChartData> getTodayChart();
}
