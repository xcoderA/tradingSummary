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
        if (!(o instanceof OutputRecord)) {
            return false;
        }
        if (this.clientInformation.equals(((OutputRecord) o).clientInformation) && this.productInformation.equals(((OutputRecord) o).productInformation)) {
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
