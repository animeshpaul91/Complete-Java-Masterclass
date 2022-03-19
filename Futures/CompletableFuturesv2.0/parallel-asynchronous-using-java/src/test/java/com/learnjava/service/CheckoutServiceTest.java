package com.learnjava.service;

import com.learnjava.domain.checkout.Cart;
import com.learnjava.domain.checkout.CheckoutResponse;
import com.learnjava.domain.checkout.CheckoutStatus;
import com.learnjava.util.DataSet;
import org.junit.jupiter.api.Test;

import static com.learnjava.util.CommonUtil.stopWatchReset;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CheckoutServiceTest {
    private static final CheckoutService checkoutService = new CheckoutService();

    @Test
    public void noOfCores() {
        int numberOfCores = Runtime.getRuntime().availableProcessors();
        System.out.println("Number of Cores: " + numberOfCores);
        assertEquals(8, numberOfCores);
    }

    @Test
    void checkout() {
        // Given
        Cart cart = DataSet.createCart(6);
        // when
        CheckoutResponse checkoutResponse = checkoutService.checkout(cart);
        assertEquals(CheckoutStatus.SUCCESS, checkoutResponse.getCheckoutStatus());
        stopWatchReset();
    }

    @Test
    void checkoutMoreItems() {
        // Given
        Cart cart = DataSet.createCart(25);
        // when
        CheckoutResponse checkoutResponse = checkoutService.checkout(cart);
        assertEquals(CheckoutStatus.FAILURE, checkoutResponse.getCheckoutStatus());
        stopWatchReset();
    }
}