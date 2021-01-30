package com.app.orderapi.validators;

import com.app.orderapi.entity.Product;
import com.app.orderapi.exceptions.ValidateServiceException;

public class ProductValidator {

    public static void save(Product product) {

        if (product.getName() == null || product.getName().trim().isEmpty()) {
            throw new ValidateServiceException("El nombre es requerido");
        }

        if (product.getName().length() > 100) {
            throw new ValidateServiceException("El nombre es muy largo, el máximo es 100 caracteres");

        }

        if (product.getPrice() == null) {
            throw new ValidateServiceException("El precio es requerido");
        }

        if (product.getPrice() < 0) {
            throw new ValidateServiceException("El precio no puede ser inferior a 0");
        }

    }
}
