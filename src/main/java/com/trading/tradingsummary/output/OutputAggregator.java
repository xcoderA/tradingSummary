package com.trading.tradingsummary.output;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Class which aggregates the output for the report
 */
@Slf4j
public class OutputAggregator {

    public static List<OutputRecord> outputList = new ArrayList();

    public static synchronized void addToOutput(OutputRecord outputRecord) {
        int index = outputList.indexOf(outputRecord);
        if (index == -1) {
            log.debug("outputRecord is not found, adding to the list");
            outputList.add(outputRecord);
        } else {
            log.debug("outputRecord already exists. Incrementing the transaction amount");
            outputList.get(index).incrementTransactionAmount(outputRecord.getTotalTransactionAmount());
        }
    }
}
