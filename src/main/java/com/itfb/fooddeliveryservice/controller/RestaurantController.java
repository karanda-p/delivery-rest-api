package com.itfb.fooddeliveryservice.controller;

import com.itfb.fooddeliveryservice.mapper.common.ProductMapper;
import com.itfb.fooddeliveryservice.mapper.common.RestaurantMapper;
import com.itfb.fooddeliveryservice.model.Error;
import com.itfb.fooddeliveryservice.model.dto.ProductDTO;
import com.itfb.fooddeliveryservice.model.dto.RestaurantDTO;
import com.itfb.fooddeliveryservice.service.ProductService;
import com.itfb.fooddeliveryservice.service.RestaurantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;


@RestController
@RequestMapping(value = "/restaurants", produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
@Tag(name = "Restaurant", description = "Restaurant endpoints")
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final ProductService productService;
    private final ProductMapper productMapper;
    private final RestaurantMapper restaurantMapper;

    @Operation(summary = "Gets all restaurants", tags = "restaurant")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Found the restaurants",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = RestaurantDTO.class)))
                    }),
            @ApiResponse(
                    responseCode = "400",
                    description = "Restaurant not found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Error.class)))
                    })
    })
    @GetMapping
    public Collection<RestaurantDTO> getAllRestaurants() {
        return restaurantMapper.domainsToDtos(restaurantService.getAll());
    }

    @Operation(summary = "Gets restaurant by id", tags = "restaurant")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Found restaurant by id",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = RestaurantDTO.class))
                    }),
            @ApiResponse(
                    responseCode = "400",
                    description = "Restaurant not found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Error.class))
                    })
    })
    @GetMapping("/{id}")
    public RestaurantDTO getRestaurantById(@PathVariable Long id) {
        return restaurantMapper.domainToDto(restaurantService.getById(id));
    }

    @Operation(summary = "Gets restaurant's products list")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Found restaurant's products",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ProductDTO.class)))
                    }),
            @ApiResponse(
                    responseCode = "400",
                    description = "Product list is empty",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Error.class)))
                    })
    })
    @GetMapping("/{id}/products")
    public Collection<ProductDTO> getAllRestaurantProducts(@PathVariable Long id) {
        return productMapper.domainsToDtos(productService.getAllProductsByRestaurant(id));
    }


    @Operation(summary = "Gets a single product by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Found product by id",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ProductDTO.class))
                    }),
            @ApiResponse(
                    responseCode = "400",
                    description = "Product not found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Error.class))
                    })
    })
    @GetMapping("/{id}/products/{productId}")
    public ProductDTO getProduct(@PathVariable Long productId) {
        return productMapper.domainToDto(productService.getProductById(productId));
    }

}
