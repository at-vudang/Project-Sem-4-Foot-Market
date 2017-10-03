package com.aptech.foodmarket.food_market.model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Where(clause = "is_active")
@Table(name = "items", uniqueConstraints={
        @UniqueConstraint(columnNames = {"name", "supplier_id"})
})
@SQLDelete(sql="UPDATE items SET is_active = 0 WHERE id = ?")
public class Item {
    public Item() {
    }
    public Item(Integer id) {
        this.id = id;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    @NotNull
    private String description;

    @Column(name = "price")
    @NotNull
    private Float price;

    @Column(name = "avatar")
    @NotNull
    private String avatar;

    @Column(name = "status")
    @NotNull
    private Boolean status;

    @Column(name = "quantity")
    @NotNull
    private Integer quantity;

    @Column(name = "expired_at")
    private Date expiredAt;

    @Column(name = "created_at")
    @NotNull
    private Date createdAt;

    @Column(name = "edited_at")
    private Date editedAt;

    @Column(name = "is_active")
    private Boolean active;

    @ManyToOne(optional=false)
    @JoinColumn(name="unit_id")
    private Unit unit;

    @ManyToOne(optional=false)
    @JoinColumn(name="supplier_id")
    private Supplier supplier;

    @OneToMany(mappedBy = "item")
    @Where(clause = "promotion_id in (select id from promotions a where a.end_at >= CURDATE())")
    private List<PromotionItem> promotionItems;

    @OneToMany(mappedBy = "item")
    private List<ImageItem> imageItems;

    @OneToMany(mappedBy = "item")
    private List<OrderItem> orderItems;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tag_categories", joinColumns
            = @JoinColumn(name = "item_id",
            referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "category_id",
                    referencedColumnName = "id"))

    private Set<Category> categories;

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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Date getExpiredAt() {
        return expiredAt;
    }

    public void setExpiredAt(Date expiredAt) {
        this.expiredAt = expiredAt;
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

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public List<PromotionItem> getPromotionItems() {
        return promotionItems;
    }

    public void setPromotionItems(List<PromotionItem> promotionItems) {
        this.promotionItems = promotionItems;
    }

    public List<ImageItem> getImageItems() {
        return imageItems;
    }

    public void setImageItems(List<ImageItem> imageItems) {
        this.imageItems = imageItems;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
