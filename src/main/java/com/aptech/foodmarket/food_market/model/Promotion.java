package com.aptech.foodmarket.food_market.model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Where(clause = "is_active")
@Table(name = "promotions")
@SQLDelete(sql="UPDATE pr SET is_active = 0 WHERE id = ?")

public class Promotion {
    public Promotion(Integer id) {
        this.id = id;
    }
    public Promotion() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "from_at")
    private Date fromAt;

    @Column(name = "end_at")
    private Date endAt;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "edited_at")
    private Date editedAt;

    @Column(name = "is_active")
    private Boolean active;

    @OneToMany(mappedBy = "promotion")
    private List<PromotionItem> promotionItems;

    @OneToMany(mappedBy = "promotion")
    private List<Order> orders;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getFromAt() {
        return fromAt;
    }

    public void setFromAt(Date fromAt) {
        this.fromAt = fromAt;
    }

    public Date getEndAt() {
        return endAt;
    }

    public void setEndAt(Date endAt) {
        this.endAt = endAt;
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

    public List<PromotionItem> getPromotionItems() {
        return promotionItems;
    }

    public void setPromotionItems(List<PromotionItem> promotionItems) {
        this.promotionItems = promotionItems;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
