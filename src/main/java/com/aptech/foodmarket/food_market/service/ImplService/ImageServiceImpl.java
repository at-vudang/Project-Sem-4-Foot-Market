package com.aptech.foodmarket.food_market.service.ImplService;

import com.aptech.foodmarket.food_market.builder.ImageItemVOBuilder;
import com.aptech.foodmarket.food_market.builder.ItemVOBuilder;
import com.aptech.foodmarket.food_market.model.ImageItem;
import com.aptech.foodmarket.food_market.repository.ImageRepository;
import com.aptech.foodmarket.food_market.service.ImageService;
import com.aptech.foodmarket.food_market.vo.ImageItemVO;
import com.aptech.foodmarket.food_market.vo.ItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    ImageRepository imageRepository;
    @Override
    public List<ImageItemVO> getImageOfItem(int id) {
        List<ImageItemVO> imageItemVOS = new ArrayList<>();
        imageRepository.findAllByItem_Id(id).stream().forEach( imageItem -> {

            ItemVO itemVO = ItemVOBuilder.anItemVO().withId(imageItem.getItem().getId())
                    .withName(imageItem.getItem().getName()).build();
            imageItemVOS.add(ImageItemVOBuilder.anImageItemVO().withId(imageItem.getId())
            .withImage(imageItem.getImage())
            .withItem(itemVO).build());
        });
        return imageItemVOS;
    }
}
