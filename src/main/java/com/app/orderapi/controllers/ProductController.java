package com.app.orderapi.controllers;


import com.app.orderapi.dto.ProductDTO;
import com.app.orderapi.entity.Product;
import com.app.orderapi.services.ProductService;
import com.app.orderapi.utils.WrapperResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    ModelMapper mapper = new ModelMapper();

    @GetMapping(value = "/products/{id}")
    public ResponseEntity<WrapperResponse<ProductDTO>> findById(@PathVariable(value = "id") Long id) {
        Product product = productService.findById(id);
        ProductDTO productDTO = mapper.map(product, ProductDTO.class);


        return new WrapperResponse(true, "success", productDTO).createResponse(HttpStatus.OK);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return new WrapperResponse(true, "success", null).createResponse(HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> findAll(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);

        List<Product> all = productService.findAll(pageable);
        List<ProductDTO> productDTOList = new ArrayList<>();

        all.stream().forEach(p -> {
            productDTOList.add(mapper.map(p, ProductDTO.class));
        });

        return new WrapperResponse(true, "success", productDTOList).createResponse(HttpStatus.OK);


    }

    @PostMapping("/products")
    public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO product) {
        Product productToTrans = new Product();
        BeanUtils.copyProperties(product, productToTrans);
        Product p = productService.create(productToTrans);
        ProductDTO productDTO = mapper.map(p, ProductDTO.class);

        return new WrapperResponse(true, "success", productDTO).createResponse(HttpStatus.CREATED);

    }

    @PutMapping("/products")
    public ResponseEntity<ProductDTO> update(@RequestBody ProductDTO product) {
        Product productToTrans = new Product();
        BeanUtils.copyProperties(product, productToTrans);
        Product p = productService.update(productToTrans);
        ProductDTO productDTO = mapper.map(p, ProductDTO.class);

        return new WrapperResponse(true, "success", productDTO).createResponse(HttpStatus.OK);
    }


}
