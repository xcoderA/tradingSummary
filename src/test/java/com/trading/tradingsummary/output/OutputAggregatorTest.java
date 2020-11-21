package com.trading.tradingsummary.output;



import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class OutputAggregatorTest {

    @Test
    public void shouldUpdateTransactionAmountWhenRecordAlreadyPresentOrElseAdd(){
        OutputRecord outputRecord1 = new OutputRecord("abc","cde",100);
        OutputRecord outputRecord2 = new OutputRecord("abc","cde",200);
        OutputAggregator.addToOutput(outputRecord1);
        assertThat(OutputAggregator.outputList.size(), is(1));
        assertThat(OutputAggregator.outputList.get(0).getTotalTransactionAmount(),is(100));
        OutputAggregator.addToOutput(outputRecord2);
        assertThat(OutputAggregator.outputList.size(), is(1));
        assertThat(OutputAggregator.outputList.get(0).getTotalTransactionAmount(),is(300));

    }

}