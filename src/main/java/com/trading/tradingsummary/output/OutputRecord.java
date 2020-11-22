package com.trading.tradingsummary.output;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
public class OutputRecord {

    String clientInformation;
    String productInformation;
    int totalTransactionAmount;

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof OutputRecord)) {
            return false;
        }
        OutputRecord outputRecord = (OutputRecord) o;
        if (this.clientInformation.equals(outputRecord.clientInformation) && this.productInformation.equals(outputRecord.productInformation)) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientInformation, productInformation);
    }

    public void incrementTransactionAmount(int increment) {
        this.totalTransactionAmount += increment;
    }
}
