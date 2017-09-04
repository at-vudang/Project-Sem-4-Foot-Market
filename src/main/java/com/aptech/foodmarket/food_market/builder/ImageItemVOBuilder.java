package com.aptech.foodmarket.food_market.builder;

import com.aptech.foodmarket.food_market.vo.ImageItemVO;
import com.aptech.foodmarket.food_market.vo.ItemVO;

public final class ImageItemVOBuilder {
    private Integer id;
    private ItemVO item;
    private String image;

    private ImageItemVOBuilder() {
    }

    public static ImageItemVOBuilder anImageItemVO() {
        return new ImageItemVOBuilder();
    }

    public ImageItemVOBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public ImageItemVOBuilder withItem(ItemVO item) {
        this.item = item;
        return this;
    }

    public ImageItemVOBuilder withImage(String image) {
        this.image = image;
        return this;
    }

    public ImageItemVO build() {
        ImageItemVO imageItemVO = new ImageItemVO();
        imageItemVO.setId(id);
        imageItemVO.setItem(item);
        imageItemVO.setImage(image);
        return imageItemVO;
    }
}
