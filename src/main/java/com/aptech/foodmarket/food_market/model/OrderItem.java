package com.aptech.foodmarket.food_market.model;

import javax.persistence.*;

@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(optional=false)
    @JoinColumn(name="item_id")
    private Item item;

    @ManyToOne(optional=false)
    @JoinColumn(name="order_id")
    private Order order;

    @Column(name = "price_offical")
    private float priceOffical;

    @Column(name = "quantity")
    private int quantity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public float getPriceOffical() {
        return priceOffical;
    }

    public void setPriceOffical(float priceOffical) {
        this.priceOffical = priceOffical;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
