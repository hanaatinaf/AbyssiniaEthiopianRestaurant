package com.pluralsight.Classes;


import java.io.*;
import java.time.format.DateTimeFormatter;


public class ReceiptWriter {

    private final String outputPath;

    // Formatter for the file name (same pattern as the assignment)
    private static final DateTimeFormatter FILE_FORMATTER =
            DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");


    public ReceiptWriter(String outputPath) {
        this.outputPath = outputPath;
    }


    public String generateFileName(Order order) {
        String timestamp = order.getDateTime().format(FILE_FORMATTER);
        return timestamp + ".txt";
    }


    public boolean save(Order order) {
        // Ensure the directory exists
        File directory = new File(outputPath);
        if (!directory.exists()) {
            boolean created = directory.mkdirs();
            if (!created) {
                System.err.println("Could not create receipts directory: " + outputPath);
                return false;
            }
        }

        String fileName = generateFileName(order);
        File receiptFile = new File(directory, fileName);

        // Try-with-resources to automatically close the writer
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(receiptFile))) {
            String receiptText = order.generateReceipt();
            writer.write(receiptText);
            return true;
        } catch (IOException e) {
            System.err.println("Error writing receipt file: " + e.getMessage());
            return false;
        }
    }
}
