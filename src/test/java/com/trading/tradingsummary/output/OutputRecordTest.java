package com.trading.tradingsummary.output;


import org.junit.Test;

import java.util.Objects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class OutputRecordTest {

    @Test
    public void shouldBeEqualIfClientAndProductInformationEqualAndHashcodesEqual() {
        OutputRecord outputRecord1 = new OutputRecord("abc", "def", 100);
        OutputRecord outputRecord2 = new OutputRecord("abc", "def", 200);
        assertThat(outputRecord1.equals(outputRecord2), is(true));
        assertThat(outputRecord1.hashCode(), is(outputRecord2.hashCode()));
    }

    @Test
    public void shouldComputeHashcodeFromClientAndProductInfo() {
        OutputRecord outputRecord = new OutputRecord("abc", "def", 100);
        int expectedHashcode = Objects.hash("abc", "def");
        assertThat(expectedHashcode, is(outputRecord.hashCode()));
    }

    @Test
    public void shouldNotBeEqualWithOtherClassObjects() {
        OutputRecord outputRecord = new OutputRecord("abc", "def", 100);
        assertThat(outputRecord.equals(new Object()), is(false));
    }

    @Test
    public void shouldNotBeEqualWhenCustomerInformationDifferent() {
        OutputRecord outputRecord1 = new OutputRecord("abc", "def", 100);
        OutputRecord outputRecord2 = new OutputRecord("zyx", "def", 100);
        assertThat(outputRecord1.equals(outputRecord2), is(false));
    }

    @Test
    public void shouldNotBeEqualWhenProductInformationDifferent() {
        OutputRecord outputRecord1 = new OutputRecord("abc", "def", 100);
        OutputRecord outputRecord2 = new OutputRecord("abc", "xyz", 100);
        assertThat(outputRecord1.equals(outputRecord2), is(false));
    }

    @Test
    public void shouldIncrementTransactionAmount() {
        OutputRecord outputRecord = new OutputRecord("abc", "def", 100);
        outputRecord.incrementTransactionAmount(100);
        assertThat(outputRecord.getTotalTransactionAmount(), is(200));
    }

}