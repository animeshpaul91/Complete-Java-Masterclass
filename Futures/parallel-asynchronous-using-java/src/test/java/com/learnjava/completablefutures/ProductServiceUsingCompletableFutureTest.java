package com.learnjava.completablefutures;

import com.learnjava.domain.Product;
import com.learnjava.service.ProductInfoService;
import com.learnjava.service.ReviewService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProductServiceUsingCompletableFutureTest {
    private final ProductServiceUsingCompletableFuture productService;

    public ProductServiceUsingCompletableFutureTest() {
        ProductInfoService productInfoService = new ProductInfoService();
        ReviewService reviewService = new ReviewService();
        this.productService = new ProductServiceUsingCompletableFuture(productInfoService, reviewService);
    }

    @Test
    void retrieveProductDetails() {
        // Given
        String productId = "ABC123";

        // When
        Product product = productService.retrieveProductDetails(productId);

        // Then
        assertNotNull(product);
        assertTrue(product.getProductInfo().getProductOptions().size() > 0);
        assertNotNull(product.getReview());
    }
}