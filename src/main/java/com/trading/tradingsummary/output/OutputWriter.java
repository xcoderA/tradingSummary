package com.trading.tradingsummary.output;

import com.trading.tradingsummary.util.FileHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

@Slf4j
@Component
public class OutputWriter {

    private static String COMMA = ",";
    private static String END_OF_LINE = "\n";
    @Autowired
    FileHelper fileHelper;

    public OutputWriter() {
        fileHelper = new FileHelper();
    }


    public void createOutputFile(List<OutputRecord> outputList) throws IOException {
        Writer writer = fileHelper.getOutputWriter();
        log.info("outputfile has {} lines", outputList.size());
        try {
            outputList.stream().forEach(or -> {
                StringBuilder builder = new StringBuilder("");
                try {
                    writer.write(builder.append(or.getClientInformation()).append(COMMA).append(or.getProductInformation()).append(COMMA)
                            .append(or.getTotalTransactionAmount()).append(END_OF_LINE).toString());
                } catch (IOException e) {
                    log.error("Exception occurred when writing to output file: ", e);
                }
            });
            log.info("successfully created output file {}",fileHelper.getOutputFilePath());
        } finally {
            writer.close();
        }
    }

}
