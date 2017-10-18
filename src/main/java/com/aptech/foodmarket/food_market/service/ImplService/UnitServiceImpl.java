package com.aptech.foodmarket.food_market.service.ImplService;

import com.aptech.foodmarket.food_market.builder.CategoryVOBuilder;
import com.aptech.foodmarket.food_market.builder.PromotionItemVOBuilder;
import com.aptech.foodmarket.food_market.builder.UnitVOBuilder;
import com.aptech.foodmarket.food_market.model.Item;
import com.aptech.foodmarket.food_market.model.PromotionItem;
import com.aptech.foodmarket.food_market.model.Unit;
import com.aptech.foodmarket.food_market.repository.PromotionItemRepository;
import com.aptech.foodmarket.food_market.repository.UnitRepository;
import com.aptech.foodmarket.food_market.service.PromotionItemService;
import com.aptech.foodmarket.food_market.service.UnitService;
import com.aptech.foodmarket.food_market.vo.ItemVO;
import com.aptech.foodmarket.food_market.vo.PromotionItemVO;
import com.aptech.foodmarket.food_market.vo.UnitVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UnitServiceImpl implements UnitService {
    @Autowired
    UnitRepository unitRepository;
    private UnitVO changUnitVO(Unit unit) {
        UnitVO unitVO = UnitVOBuilder.anUnitVO().withId(unit.getId()).withName(unit.getName())
                .withSyntax(unit.getSyntax()).build();
        return unitVO;
    }

    @Override
    public List<UnitVO> getAll() {
        List<Unit> units = unitRepository.findAll();
        List<UnitVO> unitVOs = new ArrayList<>();
        if (units != null) {
            for (Unit unit: units
                    ) {
                unitVOs.add(changUnitVO(unit));
            }
        }
        return unitVOs;
    }

    @Override
    public UnitVO deleteItem(int id) {
        Unit unit = unitRepository.findOne(id);
        UnitVO unitVO = this.convertVO(unit);
        unitRepository.delete(id);
        return unitVO;
    }

    public UnitVO convertVO(Unit unit) {
        UnitVO unitVO = UnitVOBuilder.anUnitVO()
                .withId(unit.getId())
                .withName(unit.getName())
                .withSyntax(unit.getSyntax())
                .withItems(unit.getItems())
                .build();
        return unitVO;
    }
}
