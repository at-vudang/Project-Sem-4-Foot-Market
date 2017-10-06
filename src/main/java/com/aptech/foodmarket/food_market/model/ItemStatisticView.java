package com.aptech.foodmarket.food_market.model;

import com.aptech.foodmarket.food_market.vo.SupplierVO;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "item_statistic")
public class ItemStatisticView {
    private Integer id;
    private String name;
    private Integer supplierId;
    private Integer userId;
//    private Integer orderId;
    private String supplierName;
    private Double quantityOrder;

//    @Column(name = "created_at")
//    private Date createdAt;
//
//    @Column(name = "edited_at")
//    private Date editedAt;

//    public Date getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(Date createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public Date getEditedAt() {
//        return editedAt;
//    }
//
//    public void setEditedAt(Date editedAt) {
//        this.editedAt = editedAt;
//    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }
    @Column(name = "id")
    public void setId(int id) {
        this.id = id;
    }

//    @Column(name = "order_id")
//    public int getOrderId() {
//        return orderId;
//    }
//    @Column(name = "order_id")
//    public void setOrderId(int orderId) {
//        this.orderId = orderId;
//    }


    @Column(name = "name")
    public String getName() {
        return name;
    }
    @Column(name = "name")
    public void setName(String name) {
        this.name = name;
    }


    @Column(name = "supplier_id")
    public int getSupplierId() {
        return supplierId;
    }
    @Column(name = "supplier_id")
    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }


    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }
    @Column(name = "user_id")
    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Column(name = "supplier_name")
    public String getSupplierName() {
        return supplierName;
    }
    @Column(name = "supplier_name")
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    @Column(name = "quantity_order")
    public Double getQuantityOrder() {
        return quantityOrder;
    }
    @Column(name = "quantity_order")
    public void setQuantityOrder(Double quantityOrder) {
        this.quantityOrder = quantityOrder;
    }
}
