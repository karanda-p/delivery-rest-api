package com.itfb.fooddeliveryservice.controller;

import com.itfb.fooddeliveryservice.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartItemService cartItemService;
}
