package com.trading.tradingsummary.processor;

import com.trading.tradingsummary.WorkPool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

@Component
@Slf4j
public class FileProcessor {
    @Autowired
    private WorkPool workPool;

    public void process(Reader fileReader) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                RecordProcessor recordProcessor = new RecordProcessor(new InputRecord(line));
                workPool.addToPool(recordProcessor);
            }
        }
    }
}
