package com.deepak.ProductService.query.api.projection;

import com.deepak.ProductService.command.api.data.Product;
import com.deepak.ProductService.command.api.data.ProductRepository;
import com.deepak.ProductService.command.api.model.ProductRestModel;
import com.deepak.ProductService.query.api.queries.GetProductsQuery;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class ProductProjection {

    private ProductRepository productRepository;

    public ProductProjection(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @QueryHandler
    public List<ProductRestModel> handle(GetProductsQuery getProductsQuery){
        List<Product> products = productRepository.findAll();
        List<ProductRestModel> productRestModels =
                products.stream()
                        .map(product -> ProductRestModel
                                .builder()
                                .name(product.getName())
                                .price(product.getPrice())
                                .quantity(product.getQuantity())
                                .build()
                        ).collect(Collectors.toList());
        return productRestModels;
    }
}
