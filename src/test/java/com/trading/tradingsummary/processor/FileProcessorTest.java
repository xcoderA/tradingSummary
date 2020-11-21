package com.trading.tradingsummary.processor;


import com.trading.tradingsummary.WorkPool;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class FileProcessorTest {

    @Mock
    WorkPool workPool;

    @InjectMocks
    FileProcessor fileProcessor;

    @Mock
    File file;

    @Captor
    ArgumentCaptor<RecordProcessor> captor;


    @Test
    public void shouldReadLinesAndSumbitToWorkpool() throws IOException {
        String line = "315CL  123400030001FCC   FUCME N1    20100910JPY01S 0000000000 0000000009000000000000DUSD000000000045DUSD000000000000DJPY20100819059488      000321000092100000000             O";
        InputRecord inputRecord = new InputRecord(line);
        Reader reader = new StringReader(line);

        fileProcessor.process(reader);

        verify(workPool).addToPool(captor.capture());
        RecordProcessor processor = captor.getValue();
        assertThat(processor.inputRecord.getAccountNumber(), Matchers.is(inputRecord.getAccountNumber()));
        assertThat(processor.inputRecord.getQuantityLongSign(), Matchers.is(inputRecord.getQuantityLongSign()));
        assertThat(processor.inputRecord.getQuantityShortSign(), Matchers.is(inputRecord.getQuantityShortSign()));
        assertThat(processor.inputRecord.getQuantityShort(), Matchers.is(inputRecord.getQuantityShort()));
        assertThat(processor.inputRecord.getQuantityLong(), Matchers.is(inputRecord.getQuantityLong()));
        assertThat(processor.inputRecord.getSymbol(), Matchers.is(inputRecord.getSymbol()));
        assertThat(processor.inputRecord.getProductGroupCode(), Matchers.is(inputRecord.getProductGroupCode()));
        assertThat(processor.inputRecord.getExpirationDate(), Matchers.is(inputRecord.getExpirationDate()));
        assertThat(processor.inputRecord.getExchangeCode(), Matchers.is(inputRecord.getExchangeCode()));
        assertThat(processor.inputRecord.getClientType(), Matchers.is(inputRecord.getClientType()));
        assertThat(processor.inputRecord.getClientNumber(), Matchers.is(inputRecord.getClientNumber()));
        assertThat(processor.inputRecord.getSubAccountNumber(), Matchers.is(inputRecord.getSubAccountNumber()));
    }

}