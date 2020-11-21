package com.trading.tradingsummary;

import com.trading.tradingsummary.output.OutputAggregator;
import com.trading.tradingsummary.output.OutputWriter;
import com.trading.tradingsummary.processor.InputFileProcessor;
import com.trading.tradingsummary.util.FileHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Stream;

@SpringBootApplication
@Slf4j
public class TradingSummaryApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(TradingSummaryApplication.class, args);
    }

    @Autowired
    InputFileProcessor inputFileProcessor;

    @Autowired
    OutputWriter outputWriter;

    protected static CountDownLatch countDownLatch;

    @Value("${input.file.path:Input.txt}")
    String inputFilePath;

    @Autowired
    FileHelper fileHelper;

    /**
     * Initialize the countdownLatch with count equal to number of lines in the file.
     * @param filePath
     * @throws IOException
     */
    protected void initCountDownLatch(String filePath) throws IOException {
        log.debug("Initializing countDownLatch");
        Stream<String> fileStream = Files.lines(Paths.get(filePath));
        long lineCount = fileStream.count();
        log.info("The file has {} lines", lineCount);
        if (lineCount > Integer.MAX_VALUE) {
            log.error("The line count in the file is greater than {}", Integer.MAX_VALUE);
            throw new RuntimeException("The maximum allowed lines in a file is exceeded");
        }

        TradingSummaryApplication.countDownLatch = new CountDownLatch((int) lineCount);
    }

    /**
     * count down the latch
     */
    public static void countDown() {
        TradingSummaryApplication.countDownLatch.countDown();
    }

    @Override
    public void run(String... args) throws Exception {
        Reader reader = fileHelper.getFileReader(inputFilePath);
        initCountDownLatch(inputFilePath);
        inputFileProcessor.process(reader);
        waitOnLatch();
        outputWriter.createOutputFile(OutputAggregator.outputList);
        exitApplication();
    }

    /**
     * wait for threads to finish processing the lines
     * @throws InterruptedException
     */
    public void waitOnLatch() throws InterruptedException {
        TradingSummaryApplication.countDownLatch.await();
    }

    /**
     * Exit the application when processing is finished
     */
    public void exitApplication() {
        System.exit(0);
    }

}
