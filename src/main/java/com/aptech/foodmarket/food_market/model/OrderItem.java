package com.aptech.foodmarket.food_market.model;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

@Entity
@Where(clause = "is_active = 1")
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "price_offical")
    private Double priceOffical;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "edited_at")
    private Date editedAt;

    @Column(name = "is_active")
    private Boolean active;

    @ManyToOne(optional=false)
    @JoinColumn(name="item_id")
    private Item item;

    @ManyToOne(optional=false)
    @JoinColumn(name="order_id")
    private Order order;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
        editedAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        editedAt = new Date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPriceOffical() {
        return priceOffical;
    }

    public void setPriceOffical(Double priceOffical) {
        this.priceOffical = priceOffical;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getEditedAt() {
        return editedAt;
    }

    public void setEditedAt(Date editedAt) {
        this.editedAt = editedAt;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
