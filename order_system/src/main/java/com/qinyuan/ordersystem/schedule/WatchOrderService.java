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

    //上一次执行完毕时间点之后多长时间再执行
    // long fixedDelay() default -1L;
    //上一次开始执行时间点之后多长时间再执行
    // long fixedRate() default -1L;
    @Scheduled(initialDelay = 0L, fixedDelay = 10 * 60 * 1000L)
    public void watchOrder() {
        logger.info("========watch order========");
        mCheckPostOrderService.checkPostOrder();
    }

}
