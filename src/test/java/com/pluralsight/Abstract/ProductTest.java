package com.pluralsight.Abstract;

import com.pluralsight.Abstract.Product;
import com.pluralsight.Enum.Size;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Concrete implementation for testing abstract Product class
class TestProduct extends Product {
    public TestProduct(String name, Size size) {
        super(name, size);
    }

    @Override
    public double calculatePrice() {
        return 10.0; // Fixed price for testing
    }
}

public class ProductTest {

    @Test
    void testProductCreation() {
        Product product = new TestProduct("Test Product", Size.MEDIUM);

        assertEquals("Test Product", product.getName());
        assertEquals(Size.MEDIUM, product.getSize());
    }

    @Test
    void testProductSetters() {
        Product product = new TestProduct("Test", Size.SMALL);

        product.setName("Updated Name");
        product.setSize(Size.LARGE);

        assertEquals("Updated Name", product.getName());
        assertEquals(Size.LARGE, product.getSize());
    }

    @Test
    void testCalculatePrice() {
        Product product = new TestProduct("Test", Size.MEDIUM);

        assertEquals(10.0, product.calculatePrice());
    }

    @Test
    void testToString() {
        Product product = new TestProduct("Test Item", Size.SMALL);

        String result = product.toString();
        assertTrue(result.contains("Test Item"));
        assertTrue(result.contains("Small"));
    }

    @Test
    void testProductWithNullValues() {
        Product product = new TestProduct(null, null);

        assertNull(product.getName());
        assertNull(product.getSize());
    }
}