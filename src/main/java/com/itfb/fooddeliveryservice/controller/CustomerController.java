package com.itfb.fooddeliveryservice.controller;

import com.itfb.fooddeliveryservice.mapper.common.CustomerMapper;
import com.itfb.fooddeliveryservice.model.domain.Customer;
import com.itfb.fooddeliveryservice.model.dto.CustomerDTO;
import com.itfb.fooddeliveryservice.model.dto.RestaurantDTO;
import com.itfb.fooddeliveryservice.security.Impl.UserDetailsImpl;
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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
@Tag(name = "Customer", description = "Customer endpoints")
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;


    @Operation(summary = "Create new customer")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Customer created",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = CustomerDTO.class)))
                    })
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO addCustomer(@RequestBody Customer customer) {
        return customerMapper.domainToDto(customerService.saveCustomer(customer));
    }

    @Operation(summary = "Gets authorized customer details")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Found customer",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = CustomerDTO.class)))
                    })
    })
    @GetMapping
    public CustomerDTO getCustomerByLogin(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return customerMapper.domainToDto(customerService.getCustomerByLogin(userDetails.getLogin()));
    }


    @Operation(summary = "Update customer details")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Customer details updated",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = CustomerDTO.class)))
                    })
    })
    @PutMapping
    public CustomerDTO updateCustomer(@RequestBody Customer customer) {
        return customerMapper.domainToDto(customerService.updateCustomer(customer));
    }
}
