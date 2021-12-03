package com.edn.base.testScheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class SchedulerService {

    @Scheduled(fixedDelayString = "${app.scheduling-delay}", initialDelayString = "${app.scheduling-delay}")
    public void testProcessScheduled() {
      log.info("process has been triggered date:{}", LocalDateTime.now());
    }

}
