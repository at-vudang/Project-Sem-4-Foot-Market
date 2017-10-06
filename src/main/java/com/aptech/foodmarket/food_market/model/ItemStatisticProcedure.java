package com.aptech.foodmarket.food_market.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NamedStoredProcedureQuery(name = "item_statistic", procedureName = "item_statistic", parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "begindate", type = Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "endDate", type = Integer.class)})
public class ItemStatisticProcedure {
    private int id;
    private String name;
    private int supplierId;
    private int userId;
    private String supplierName;
    private int quantityOrder;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NotNull
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    @Column(name = "supplier_id")
    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    @NotNull
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @NotNull
    @Column(name = "supplier_name")
    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    @Column(name = "quantity_order")
    public int getQuantityOrder() {
        return quantityOrder;
    }

    public void setQuantityOrder(int quantityOrder) {
        this.quantityOrder = quantityOrder;
    }
}


