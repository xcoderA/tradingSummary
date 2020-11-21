package com.trading.tradingsummary.output;

import com.trading.tradingsummary.util.FileHelper;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OutputWriterTest {


    List<OutputRecord> outputList;
    OutputWriter outputWriter;
    @Mock
    Writer writer;
    @Mock
    FileHelper fileHelper;
    @Captor
    ArgumentCaptor<String> outputLineCaptor;

    @Before
    public void setup() {
        outputList = new ArrayList<>();
        outputList.add(new OutputRecord("abc", "def", 10));
        outputWriter = spy(new OutputWriter());
    }

    @Test
    public void shouldWriteOutoutToFile() throws IOException {

        given(fileHelper.getOutputWriter()).willReturn(writer);
        outputWriter.fileHelper = fileHelper;

        outputWriter.createOutputFile(outputList);

        verify(writer).write(outputLineCaptor.capture());
        assertThat(outputLineCaptor.getValue(), Matchers.is("abc,def,10\n"));

    }

    @Test
    public void shouldHandleExceptionsInWrite() throws IOException {
        given(fileHelper.getOutputWriter()).willReturn(writer);
        outputWriter.fileHelper = fileHelper;
        doThrow(IOException.class).when(writer).write(anyString());

        outputWriter.createOutputFile(outputList);

        verify(writer).write(outputLineCaptor.capture());

        assertThat(outputLineCaptor.getValue(), Matchers.is("abc,def,10\n"));
    }


}