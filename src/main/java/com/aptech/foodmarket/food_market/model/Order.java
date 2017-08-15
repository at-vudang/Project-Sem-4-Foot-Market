package com.aptech.foodmarket.food_market.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(optional=false)
    @JoinColumn(name="user_id")
    private  User user;

    @ManyToOne(optional=false)
    @JoinColumn(name="promotion_id")
    private  Promotion promotion;

    @ManyToOne(optional=false)
    @JoinColumn(name="ship_id")
    private  Ship ship;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

    @Column(name = "trans_at")
    private Date transportedAt;

    @Column(name = "address")
    private Date address;

    @Column(name = "status")
    private byte status;

    @Column(name = "phone",length = 15)
    private String phone;

    @Column(name = "note")
    private String note;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "edited_at")
    private Date editedAt;

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getTransportedAt() {
        return transportedAt;
    }

    public void setTransportedAt(Date transportedAt) {
        this.transportedAt = transportedAt;
    }

    public Date getAddress() {
        return address;
    }

    public void setAddress(Date address) {
        this.address = address;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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
}
