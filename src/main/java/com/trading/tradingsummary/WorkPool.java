package com.trading.tradingsummary;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Component
@Slf4j
public class WorkPool {

    Executor threadPool;

    public WorkPool(@Value("${threadpool.size}") int threadPoolSize) {
        threadPool = Executors.newFixedThreadPool(threadPoolSize);
    }

    public void addToPool(Runnable task) {
        threadPool.execute(task);
    }


}
