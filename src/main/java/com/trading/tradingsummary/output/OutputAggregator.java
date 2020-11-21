package com.trading.tradingsummary.output;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class OutputAggregator {

    public static List<OutputRecord> outputList = new ArrayList();

    public static synchronized void addToOutput(OutputRecord outputRecord) {
        int index = outputList.indexOf(outputRecord);
        if (index == -1) {
            outputList.add(outputRecord);
        } else {
            outputList.get(index).incrementTransactionAmount(outputRecord.getTotalTransactionAmount());
        }

    }

}
