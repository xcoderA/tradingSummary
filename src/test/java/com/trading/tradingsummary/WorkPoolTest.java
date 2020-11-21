package com.trading.tradingsummary;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.concurrent.Executor;


@RunWith(MockitoJUnitRunner.class)
public class WorkPoolTest {

    @Mock
    Runnable runnable;

    @Test
    public void shouldAddToThreadpoolWhenAddToPoolCalled() {
        WorkPool workPool = new WorkPool(10);
        Executor threadPool = Mockito.spy(workPool.threadPool);
        workPool.threadPool = threadPool;
        workPool.addToPool(runnable);
        Mockito.verify(workPool.threadPool, Mockito.times(1)).execute(runnable);
    }
}