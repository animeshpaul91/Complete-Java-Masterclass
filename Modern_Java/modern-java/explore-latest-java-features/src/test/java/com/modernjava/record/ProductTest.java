package com.modernjava.record;

import com.modernjava.records.Product;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductTest {

    private static final Logger LOG = LoggerFactory.getLogger(ProductTest.class);
    @Test
    void createProduct() {
        // given
        final var name = "IPhone";
        final var cost = new BigDecimal("999.999");
        final var type = "Electronics";
        final Product product = new Product(name, cost, type);
        // when
        LOG.info(() -> "product = " + product);
        assertEquals(name, product.name());
        assertEquals(cost, product.cost());
        assertEquals(type, product.type());
    }
}