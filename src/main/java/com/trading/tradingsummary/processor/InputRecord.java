package com.trading.tradingsummary.processor;

public class InputRecord {

    private String line;

    public InputRecord(String line) {
        this.line = line;
    }

    public String getClientType() {
        return line.substring(3, 7);
    }

    public String getClientNumber() {
        return line.substring(7, 11);
    }

    public String getAccountNumber() {
        return line.substring(11, 15);
    }

    public String getSubAccountNumber() {
        return line.substring(15, 19);
    }

    public String getExchangeCode() {
        return line.substring(27, 31);
    }

    public String getProductGroupCode() {
        return line.substring(25, 27);
    }

    public String getSymbol() {
        return line.substring(31, 37);
    }

    public String getExpirationDate() {
        return line.substring(37, 45);
    }

    public String getQuantityLongSign() {
        return line.substring(51, 52);
    }

    public String getQuantityLong() {
        return line.substring(52, 62);
    }

    public String getQuantityShortSign() {
        return line.substring(62, 63);
    }

    public String getQuantityShort() {
        return line.substring(63, 73);
    }
}
