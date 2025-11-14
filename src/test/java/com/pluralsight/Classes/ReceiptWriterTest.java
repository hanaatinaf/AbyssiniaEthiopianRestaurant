package com.pluralsight.Classes;

import com.pluralsight.Classes.Drink.Drink;

import com.pluralsight.Enum.Size;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class ReceiptWriterTest {

    @Test
    void save_createsReceiptFile() {
        // Use a test-specific folder so we don't mix with real receipts
        String testDirName = "test-receipts";

        Order order = new Order();
        order.addProduct(new Drink("Drink", Size.SMALL, "Shai"));

        ReceiptWriter writer = new ReceiptWriter(testDirName);

        boolean saved = writer.save(order);

        assertTrue(saved, "save(order) should return true");

        File dir = new File(testDirName);
        assertTrue(dir.exists(), "Directory " + testDirName + " should exist");

        File[] files = dir.listFiles();
        assertNotNull(files);
        assertTrue(files.length > 0, "Receipt directory should contain at least one file");
    }
}
