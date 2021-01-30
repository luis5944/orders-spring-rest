package com.app.orderapi.services;

import com.app.orderapi.entity.Product;
import com.app.orderapi.exceptions.GeneralServiceException;
import com.app.orderapi.exceptions.NoDataFoundException;
import com.app.orderapi.exceptions.ValidateServiceException;
import com.app.orderapi.repository.ProductRepository;
import com.app.orderapi.validators.ProductValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    private static final Logger log = LoggerFactory.getLogger(ProductService.class);


    public Product findById(@PathVariable(value = "id") Long id) {
        try {
            Product product = productRepository.findById(id)
                    .orElseThrow(() -> new NoDataFoundException("No existe"));

            return product;
        } catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException();
        }
    }

    @Transactional
    public void delete(@PathVariable Long id) {
        try {

            Product pr = productRepository.findById(id).orElseThrow(() -> new NoDataFoundException("No existe"));
            productRepository.delete(pr);
        } catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException();
        }


    }

    public List<Product> findAll(Pageable pageable) {
        try {

            return productRepository.findAll(pageable).toList();

        } catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException();
        }

    }

    @Transactional
    public Product create(Product product) {
        try {
            ProductValidator.save(product);
            return productRepository.save(product);

        } catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException();
        }

    }

    @Transactional
    public Product update(Product product) {
        try {
            ProductValidator.save(product);

            Product pr = productRepository.findById(product.getId()).orElseThrow(() -> new NoDataFoundException("No existe"));
            pr.setName(product.getName());
            pr.setPrice(product.getPrice());
            Product save = productRepository.save(pr);

            return save;

        } catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException();
        }
    }

}
