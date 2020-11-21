package com.trading.tradingsummary.processor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(MockitoJUnitRunner.class)
public class InputRecordTest {

    private static String line = "315CL  123400030001FCC   FUCME N1    20100910JPY01S 0000000000 0000000009000000000000DUSD000000000045DUSD000000000000DJPY20100819059488      000321000092100000000             O";

    @Test
    public void shouldParseValuesCorrectly() {
        InputRecord inputRecord = new InputRecord(line);

        assertThat(inputRecord.getAccountNumber(), is("0003"));
        assertThat(inputRecord.getSubAccountNumber(), is("0001"));
        assertThat(inputRecord.getClientNumber(), is("1234"));
        assertThat(inputRecord.getClientType(), is("CL  "));
        assertThat(inputRecord.getExchangeCode(), is("CME "));
        assertThat(inputRecord.getExpirationDate(), is("20100910"));
        assertThat(inputRecord.getProductGroupCode(), is("FU"));
        assertThat(inputRecord.getQuantityLong(), is("0000000000"));
        assertThat(inputRecord.getQuantityShort(), is("0000000009"));
        assertThat(inputRecord.getSymbol(), is("N1    "));
        assertThat(inputRecord.getQuantityLongSign(), is(" "));
        assertThat(inputRecord.getQuantityShortSign(), is(" "));
    }
}