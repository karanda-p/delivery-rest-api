package com.itfb.fooddeliveryservice.controller;

import com.itfb.fooddeliveryservice.mapper.common.OrderMapper;
import com.itfb.fooddeliveryservice.model.domain.order.Order;
import com.itfb.fooddeliveryservice.model.dto.OrderDTO;
import com.itfb.fooddeliveryservice.repository.CustomerRepository;
import com.itfb.fooddeliveryservice.security.Impl.UserDetailsImpl;
import com.itfb.fooddeliveryservice.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collection;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@Tag(name = "Order", description = "Order endpoints")
public class OrderController {

    private final OrderMapper orderMapper;
    private final OrderService orderService;
    private final CustomerRepository customerRepository;

    @Operation(summary = "Gets all orders by customer's id from session")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Found orders",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = OrderDTO.class)))
                    })
    })
    @GetMapping("/orders")
    public Collection<OrderDTO> getAllOrdersByCustomerId(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return orderMapper.domainsToDtos(orderService.getAllOrdersByCustomerId(customerRepository.findCustomerByLogin(userDetails.getLogin()).get().getId()));
    }


    @Operation(summary = "Gets order by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Found order",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = OrderDTO.class))
                    })
    })
    @GetMapping("/orders/{orderId}")
    public OrderDTO getOrderById(@PathVariable Long orderId) {
        return orderMapper.domainToDto(orderService.getOrderById(orderId));
    }

    @Operation(summary = "Create order")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Order created",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = OrderDTO.class))
                    })
    })
    @PostMapping("/orders")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    OrderDTO createOrder(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody Order order) throws IOException {
        return orderMapper.domainToDto(orderService.createNewOrder(userDetails.getUsername(), order));
    }

    @Operation(summary = "Change order status")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Status changed",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = OrderDTO.class))
                    })
    })
    @PutMapping("/orders")
    public OrderDTO changeOrderStatus(@RequestBody Order order) {
        return orderMapper.domainToDto(orderService.changeOrderStatus(order));
    }
}
