package com.aptech.foodmarket.food_market.model;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "tag_categories")
public class Tag_Categories {
    public int getTag_id() {
        return tag_id;
    }

    public void setTag_id(int tag_id) {
        this.tag_id = tag_id;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "tag_id", unique = true, nullable = false)
    private int tag_id;
}
