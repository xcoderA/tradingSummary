package com.trading.tradingsummary.processor;

import com.trading.tradingsummary.TradingSummaryApplication;
import com.trading.tradingsummary.output.OutputAggregator;
import com.trading.tradingsummary.output.OutputRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(MockitoJUnitRunner.class)
public class RecordProcessorTest {

    @Test
    public void shouldAddToOutputAndCountdown() {
        InputRecord inputRecord = new InputRecord("315CL  123400030001FCC   FUCME N1    20100910JPY01S 0000000000 0000000009000000000000DUSD000000000045DUSD000000000000DJPY20100819059488      000321000092100000000             O");

        try (MockedStatic<OutputAggregator> aggregatorMockedStatic = Mockito.mockStatic(OutputAggregator.class)) {
            try (MockedStatic<TradingSummaryApplication> tradingSummaryApplicationMockedStatic = Mockito.mockStatic(com.trading.tradingsummary.TradingSummaryApplication.class)) {
                ArgumentCaptor<OutputRecord> captor = ArgumentCaptor.forClass(OutputRecord.class);
                RecordProcessor recordProcessor = new RecordProcessor(inputRecord);
                recordProcessor.run();
                aggregatorMockedStatic.verify(() -> OutputAggregator.addToOutput(captor.capture()));
                assertThat(captor.getValue().getClientInformation(), is(new StringBuilder(inputRecord.getClientType()).append(inputRecord.getClientNumber()).append(inputRecord.getAccountNumber()).append(inputRecord.getSubAccountNumber()).toString()));
                assertThat(captor.getValue().getProductInformation(), is(new StringBuilder(inputRecord.getExchangeCode()).append(inputRecord.getProductGroupCode()).append(inputRecord.getSymbol()).append(inputRecord.getExpirationDate()).toString()));
                assertThat(captor.getValue().getTotalTransactionAmount(), is(Integer.parseInt(inputRecord.getQuantityLong()) - Integer.parseInt(inputRecord.getQuantityShort())));
                tradingSummaryApplicationMockedStatic.verify(() -> {
                    TradingSummaryApplication.countDown();
                });
            }
        }
    }
}