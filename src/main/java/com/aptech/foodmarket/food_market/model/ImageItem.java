package com.aptech.foodmarket.food_market.model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

@Entity
@Where(clause = "is_active")
@Table(name = "image_items")

public class ImageItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(optional=false)
    @JoinColumn(name="item_id")
    private Item item;

    @Column(name = "image")
    private String image;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "edited_at")
    private Date editedAt;

    @Column(name = "is_active")
    private Boolean active;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
        editedAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        editedAt = new Date();
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
