package com.trading.tradingsummary.util;

import java.io.*;

public class FileHelper {

    public Writer getOutputWriter() throws IOException {
        File file = new File("Output.txt");
        return new FileWriter(file);
    }

    public Reader getFileReader(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        return new FileReader(file);
    }
}
