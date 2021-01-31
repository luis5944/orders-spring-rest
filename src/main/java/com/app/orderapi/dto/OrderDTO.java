package com.app.orderapi.dto;

import java.util.List;

public class OrderDTO {

    private Long id;
    private String regDate;
    private List<OrderLineDTO> lines;
    private Double total;

    public OrderDTO() {
    }

    public OrderDTO(Long id, String regDate, List<OrderLineDTO> lines, Double total) {
        this.id = id;
        this.regDate = regDate;
        this.lines = lines;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public List<OrderLineDTO> getLines() {
        return lines;
    }

    public void setLines(List<OrderLineDTO> lines) {
        this.lines = lines;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
