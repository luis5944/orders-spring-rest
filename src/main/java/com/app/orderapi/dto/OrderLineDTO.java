package com.app.orderapi.dto;

public class OrderLineDTO {

    private Long id;

    private ProductDTO product;
    private Double price;
    private Double quantity;
    private Double total;

    public OrderLineDTO(Long id, ProductDTO product, Double price, Double quantity, Double total) {
        this.id = id;
        this.product = product;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
    }

    public OrderLineDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
