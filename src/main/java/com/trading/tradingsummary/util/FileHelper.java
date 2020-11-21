package com.trading.tradingsummary.util;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
@Getter
public class FileHelper {
    @Value("${output.file.path:Output.txt}")
    String outputFilePath;

    public Writer getOutputWriter() throws IOException {
        File file = new File(outputFilePath);
        return new FileWriter(file);
    }

    public Reader getFileReader(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        return new FileReader(file);
    }
}
