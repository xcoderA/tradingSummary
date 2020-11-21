package com.trading.tradingsummary.processor;

import com.trading.tradingsummary.TradingSummaryApplication;
import com.trading.tradingsummary.output.OutputAggregator;
import com.trading.tradingsummary.output.OutputRecord;

public class RecordProcessor implements Runnable {

    InputRecord inputRecord;

    public RecordProcessor(InputRecord inputRecord) {
        this.inputRecord = inputRecord;
    }

    @Override
    public void run() {
        String clientInformation = new StringBuilder(inputRecord.getClientType()).append(inputRecord.getClientNumber()).append(inputRecord.getAccountNumber()).append(inputRecord.getSubAccountNumber()).toString();
        String productInformation = new StringBuilder(inputRecord.getExchangeCode()).append(inputRecord.getProductGroupCode()).append(inputRecord.getSymbol()).append(inputRecord.getExpirationDate()).toString();
        int netTotal = Integer.parseInt(inputRecord.getQuantityLong()) - Integer.parseInt(inputRecord.getQuantityShort());
        OutputAggregator.addToOutput(new OutputRecord(clientInformation, productInformation, netTotal));
        TradingSummaryApplication.countDown();
    }


}
