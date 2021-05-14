package com.itfb.fooddeliveryservice.controller;

import com.itfb.fooddeliveryservice.mapper.common.CartItemMapper;
import com.itfb.fooddeliveryservice.model.domain.Product;
import com.itfb.fooddeliveryservice.model.domain.cart.CartItem;
import com.itfb.fooddeliveryservice.model.dto.CartItemDTO;
import com.itfb.fooddeliveryservice.model.dto.CustomerDTO;
import com.itfb.fooddeliveryservice.security.Impl.UserDetailsImpl;
import com.itfb.fooddeliveryservice.service.CartService;
import com.itfb.fooddeliveryservice.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(value = "/", consumes = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
@Tag(name = "Cart", description = "Cart endpoints")
public class CartController {

    private final CartItemMapper cartItemMapper;
    private final CartService cartService;
    private final CustomerService customerService;

    @Operation(summary = "Gets cart items by customer's id from session")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Found cart items",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = CartItemDTO.class)))
                    })
    })
    @GetMapping("/cart/items")
    public Collection<CartItemDTO> getAllCartItemsByCustomerId(
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return cartItemMapper.domainsToDtos(customerService
                .getCustomerByLogin(userDetails.getLogin())
                .getCart()
                .getCartItems());
    }

    @Operation(summary = "Add product to cart")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Product added to cart",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CartItemDTO.class))
                    })
    })
    @PostMapping("/cart/items")
    @ResponseStatus(HttpStatus.CREATED)
    public CartItemDTO addProductToCart(@RequestBody Product product
            , @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return cartItemMapper.domainToDto(cartService.addProductToCart(product, userDetails.getLogin()));
    }

    @Operation(summary = "Delete cart by customer's id from session")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Cart deleted")
    })
    @DeleteMapping("/cart")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCartByLogin(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        customerService.getCustomerByLogin(userDetails.getLogin()).setCart(null);
        cartService.deleteCartById(customerService.getCustomerByLogin(userDetails.getLogin()).getCartId());
    }

    @Operation(summary = "Delete cart item")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Cart item deleted")
    })
    @DeleteMapping("/cart/items")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCartItemFromCart(@AuthenticationPrincipal UserDetailsImpl userDetails
            , @RequestBody CartItem cartItem) {
        cartService.deleteCartItemFromCart(userDetails.getLogin(), cartItem);
    }
}
