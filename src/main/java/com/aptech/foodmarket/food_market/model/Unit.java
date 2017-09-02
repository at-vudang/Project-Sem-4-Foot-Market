package com.aptech.foodmarket.food_market.model;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Where(clause = "is_active = 1")
@Table(name = "units")
public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name",length = 50)
    private String name;

    @Column(name = "syntax",length = 3)
    private String syntax;

    @OneToMany(mappedBy = "unit")
    private List<Item> items;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSyntax() {
        return syntax;
    }

    public void setSyntax(String syntax) {
        this.syntax = syntax;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
