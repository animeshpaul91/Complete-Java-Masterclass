package com.learnjava.completablefutures;

import com.learnjava.domain.Product;
import com.learnjava.service.ProductInfoService;
import com.learnjava.service.ReviewService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceUsingCompletableFutureTest {
    private final ProductServiceUsingCompletableFuture productService;

    public ProductServiceUsingCompletableFutureTest() {
        ProductInfoService productInfoService = new ProductInfoService();
        ReviewService reviewService = new ReviewService();
        this.productService = new ProductServiceUsingCompletableFuture(productInfoService, reviewService);
    }

    @Test
    void testRetrieveProductDetailsClient() {
        // Given
        String productId = "ABC123";

        // When
        Product product = productService.retrieveProductDetailsClient(productId);

        // Then
        assertNotNull(product);
        assertTrue(product.getProductInfo().getProductOptions().size() > 0);
        assertNotNull(product.getReview());
    }

    @Test
    void testRetrieveProductDetailsServer() {
        String productId = "ABC123";
        productService.retrieveProductDetailsServer(productId)
                .thenAccept(product -> {
                    assertNotNull(product);
                    assertTrue(product.getProductInfo().getProductOptions().size() > 0);
                    assertNotNull(product.getReview());
                }).join();
    }
}