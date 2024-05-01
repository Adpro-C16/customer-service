package id.ac.ui.cs.advprog.heymart.customerservice.service;

import id.ac.ui.cs.advprog.heymart.customerservice.dto.ShoppingCartDto;
import id.ac.ui.cs.advprog.heymart.customerservice.dto.request.CartItemRequest;
import id.ac.ui.cs.advprog.heymart.customerservice.model.ShoppingCart;
import org.apache.coyote.BadRequestException;

public interface ShoppingCartService {
    ShoppingCartDto addItem(CartItemRequest request) throws BadRequestException;

    ShoppingCartDto removeItem(CartItemRequest request) throws BadRequestException;

    ShoppingCartDto getCartDtoByUserId(Long userId);

}
