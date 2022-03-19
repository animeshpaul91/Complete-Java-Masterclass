package com.learnjava.service;

import com.learnjava.domain.checkout.Cart;
import com.learnjava.domain.checkout.CartItem;
import com.learnjava.domain.checkout.CheckoutResponse;
import com.learnjava.domain.checkout.CheckoutStatus;

import java.util.List;
import java.util.stream.Collectors;

import static com.learnjava.util.CommonUtil.startTimer;
import static com.learnjava.util.CommonUtil.timeTaken;

public class CheckoutService {
    private static final PriceValidatorService priceValidatorService = new PriceValidatorService();

    public CheckoutResponse checkout(Cart cart) {
        startTimer();
        List<CartItem> priceValidationList = cart.getCartItemList().stream()
                .peek(cartItem -> {
                    boolean isCartItemInvalid = priceValidatorService.isCartItemInvalid(cartItem);
                    cartItem.setExpired(isCartItemInvalid);
                }).filter(CartItem::isExpired)
                .collect(Collectors.toList());

        timeTaken();
        return (priceValidationList.size() > 0) ? new CheckoutResponse(CheckoutStatus.FAILURE, priceValidationList) : new CheckoutResponse(CheckoutStatus.SUCCESS);
    }
}
