package com.fifty.fifty.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fifty.fifty.service.FilesServiceImpl;

@Component
public class FileCleanScheduler {
    
    @Autowired
    private FilesServiceImpl filesServiceImpl;



    // 매일 자정 실행 예시
    @Scheduled(cron = "0 36 1 * * ?")
    public void scheduledCleanup() throws Exception {
        filesServiceImpl.cleanupFiles();
    }
}

