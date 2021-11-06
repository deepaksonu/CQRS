package com.deepak.ProductService.query.api.controller;

import com.deepak.ProductService.command.api.model.ProductRestModel;
import com.deepak.ProductService.query.api.queries.GetProductsQuery;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/products")
public class ProductQueryController {

    private QueryGateway queryGateway;

    public ProductQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping
    public List<ProductRestModel> getAllProducts(){
        GetProductsQuery getProductsQuery = new GetProductsQuery();
        List<ProductRestModel> productRestModels = queryGateway.query(getProductsQuery, ResponseTypes.multipleInstancesOf(ProductRestModel.class))
                .join();
        return productRestModels;
    }
}
