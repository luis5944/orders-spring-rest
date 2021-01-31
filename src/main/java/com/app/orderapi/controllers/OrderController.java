package com.app.orderapi.controllers;

import com.app.orderapi.dto.OrderDTO;
import com.app.orderapi.dto.ProductDTO;
import com.app.orderapi.entity.Order;
import com.app.orderapi.utils.WrapperResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OrderController {
    ModelMapper mapper = new ModelMapper();

    @GetMapping("/orders")
    public ResponseEntity<WrapperResponse<List<OrderDTO>>> findAll(
            @RequestParam(name = "pageNumber", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize", required = false, defaultValue = "5") int pageSize
    ) {
        Pageable page = PageRequest.of(pageNumber, pageSize);

        List<Order> orders = null;
        List<OrderDTO> orderDTOList = new ArrayList<>();
        orders.stream().forEach(o -> {

            orderDTOList.add(mapper.map(o, OrderDTO.class));
        });

        return new WrapperResponse(true, "success", orderDTOList).createResponse();
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<WrapperResponse<OrderDTO>> findById(@PathVariable(name = "id") Long id) {

        Order order = null;
        OrderDTO orderDTO = new OrderDTO();
        mapper.map(order, orderDTO);

        return new WrapperResponse(true, "success", orderDTO).createResponse();
    }

    @PostMapping("/orders")
    public ResponseEntity<WrapperResponse<OrderDTO>> create(@RequestBody OrderDTO order) {
        /*
           Product productToTrans = new Product();
        BeanUtils.copyProperties(product, productToTrans);
        Product p = productService.create(productToTrans);
        ProductDTO productDTO = mapper.map(p, ProductDTO.class);
         */
        Order orderToTrans = new Order();
        mapper.map(order, orderToTrans);
        Order o = null; //el servicio
        OrderDTO orderDTO = mapper.map(o, OrderDTO.class);

        return new WrapperResponse(true, "success", orderDTO).createResponse(HttpStatus.CREATED);
    }


    @PutMapping("/orders")
    public ResponseEntity<WrapperResponse<OrderDTO>> update(@RequestBody OrderDTO order) {
        /*
           Product productToTrans = new Product();
        BeanUtils.copyProperties(product, productToTrans);
        Product p = productService.create(productToTrans);
        ProductDTO productDTO = mapper.map(p, ProductDTO.class);
         */
        Order orderToTrans = new Order();
        mapper.map(order, orderToTrans);
        Order o = null; //el servicio
        OrderDTO orderDTO = mapper.map(o, OrderDTO.class);

        return new WrapperResponse(true, "success", orderDTO).createResponse();
    }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
       // productService.delete(id);
        return new WrapperResponse(true, "success", null).createResponse();
    }


}
