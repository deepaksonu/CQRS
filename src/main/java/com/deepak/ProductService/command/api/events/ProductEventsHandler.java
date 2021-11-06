package com.deepak.ProductService.command.api.events;

import com.deepak.ProductService.command.api.data.Product;
import com.deepak.ProductService.command.api.data.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ProcessingGroup("product")
public class ProductEventsHandler {

    private ProductRepository productRepository;

    public ProductEventsHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @EventHandler
    public void on(ProductCreatedEvent event) throws Exception {
        log.info("In ProductEventsHandler : " + event);
        Product product = new Product();
        BeanUtils.copyProperties(event,product) ;
        productRepository.save(product);
        //throw new Exception("Exception occurred");
    }

    @ExceptionHandler
    public void handle(Exception e) throws Exception {
        throw e;
    }


}
