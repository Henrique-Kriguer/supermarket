package com.henriquekriguer.github.supermarket.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "supermarket")
public class Product implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "description", nullable = false, length = 100)
    private String description;
    @Column(name = "unit", nullable = false, length = 10)
    private String unit;
    @Column(name = "unit_price", nullable = false, length = 20)
    private double unitPrice;
    @Column(name = "quantity", nullable = false, length = 100)
    private double quantity;

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Product product)) return false;
        return Double.compare(getUnitPrice(), product.getUnitPrice()) == 0 && Double.compare(getQuantity(), product.getQuantity()) == 0 && Objects.equals(getId(), product.getId()) && Objects.equals(getDescription(), product.getDescription()) && Objects.equals(getUnit(), product.getUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDescription(), getUnit(), getUnitPrice(), getQuantity());
    }
}
