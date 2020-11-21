package com.trading.tradingsummary;

import com.trading.tradingsummary.output.OutputWriter;
import com.trading.tradingsummary.processor.InputFileProcessor;
import com.trading.tradingsummary.util.FileHelper;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TradingSummaryApplicationTest {

    @Mock
    Stream<String> mockStream;

    @Mock
    FileHelper fileHelper;
    @Mock
    Reader reader;
    @Mock
    InputFileProcessor inputFileProcessor;
    @Mock
    OutputWriter outputWriter;

    @Test
    public void shouldProcessAndWait() throws Exception {
        assertNull(TradingSummaryApplication.countDownLatch);
        try (MockedStatic<Files> filesMockedStatic = Mockito.mockStatic(Files.class)) {
            filesMockedStatic.when(() -> Files.lines(any())).thenReturn(mockStream);
            given(mockStream.count()).willReturn(10l);
            TradingSummaryApplication application = Mockito.spy(new TradingSummaryApplication());
            application.fileHelper = fileHelper;
            application.inputFilePath = "abc";
            application.inputFileProcessor = inputFileProcessor;
            application.outputWriter = outputWriter;

            given(fileHelper.getFileReader(any())).willReturn(reader);
            doNothing().when(inputFileProcessor).process(reader);
            doNothing().when(application).exitApplication();
            doNothing().when(application).waitOnLatch();

            application.run(new String[]{""});

            verify(inputFileProcessor).process(reader);
            verify(application).waitOnLatch();

            TradingSummaryApplication.countDownLatch = null;

        }
    }

    @Test
    public void shouldInitCountdownLatch() throws IOException {
        assertNull(TradingSummaryApplication.countDownLatch);
        try (MockedStatic<Files> filesMockedStatic = Mockito.mockStatic(Files.class)) {
            filesMockedStatic.when(() -> Files.lines(any())).thenReturn(mockStream);
            given(mockStream.count()).willReturn(10l);

            new TradingSummaryApplication().initCountDownLatch("abc");

            assertNotNull(TradingSummaryApplication.countDownLatch);
            assertThat(TradingSummaryApplication.countDownLatch.getCount(), Matchers.is(10l));
        }
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowExceptionWhenFileLinesGtIntegerMax() throws IOException {
        assertNull(TradingSummaryApplication.countDownLatch);
        try (MockedStatic<Files> filesMockedStatic = Mockito.mockStatic(Files.class)) {
            filesMockedStatic.when(() -> Files.lines(any())).thenReturn(mockStream);
            given(mockStream.count()).willReturn(Integer.MAX_VALUE + 1l);

            new TradingSummaryApplication().initCountDownLatch("abc");

        }
    }


}