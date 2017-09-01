package com.aptech.foodmarket.food_market.model;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

@Entity
@Where(clause = "is_active = 1")
@Table(name = "promotion_items")
public class PromotionItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "percent",length = 2)
    private Integer percent;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "edited_at")
    private Date editedAt;

    @Column(name = "is_active")
    private Boolean active;

    @ManyToOne(optional=false)
    @JoinColumn(name="promotion_id")
    private Promotion promotion;

    @ManyToOne(optional=false)
    @JoinColumn(name="item_id")
    private Item item;

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

    public Integer getPercent() {
        return percent;
    }

    public void setPercent(Integer percent) {
        this.percent = percent;
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

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
