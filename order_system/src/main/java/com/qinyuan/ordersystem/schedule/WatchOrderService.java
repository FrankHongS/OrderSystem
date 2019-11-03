package com.qinyuan.ordersystem.schedule;

import com.qinyuan.ordersystem.service.CheckPostOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@EnableScheduling
@Component
public class WatchOrderService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private final CheckPostOrderService mCheckPostOrderService;

    public WatchOrderService(CheckPostOrderService checkPostOrderService) {
        this.mCheckPostOrderService = checkPostOrderService;
    }

    @Scheduled(cron = "0 0/10 * * * ?")
    public void watchOrder() {
        logger.info("hello world");
        mCheckPostOrderService.checkPostOrder();
    }

}
