package com.app.orderapi.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ORDER_LINES")
public class OrderLine {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "FK_ORDER", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "FK_PRODUCT", nullable = false)
    private Product product;

    @Column(name = "PRICE", nullable = false)
    private Double price;

    @Column(name = "QUANTITY", nullable = false)
    private Double quantity;

    @Column(name = "TOTAL", nullable = false)
    private Double total;

    public OrderLine() {
    }

    public OrderLine(Long id, Order order, Product product, Double price, Double quantity, Double total) {
        this.id = id;
        this.order = order;
        this.product = product;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderLine orderLine = (OrderLine) o;
        return Objects.equals(id, orderLine.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
