package com.fifty.fifty.scheduler;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.fifty.fifty.service.YoutubeService;


@Service
public class YoutubeSchedulerService {

    @Autowired
    private YoutubeService chartService;


@Scheduled(cron = "0 0 9 * * *") // 매일 오전 9시
public void scheduledYoutubeUpdate() throws IOException, InterruptedException {
    chartService.fetchTop10YoutubeVideos();
}
}