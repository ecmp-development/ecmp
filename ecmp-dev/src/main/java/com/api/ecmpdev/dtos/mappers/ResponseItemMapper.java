package com.api.ecmpdev.dtos.mappers;

import com.api.ecmpdev.dtos.ResponseItem;
import com.api.ecmpdev.models.Item;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ResponseItemMapper implements Function<Item, ResponseItem> {

    @Override
    public ResponseItem apply(Item item) {
        return new ResponseItem(
                item.getName(),
                item.getType(),
                item.getQuantity(),
                item.getDescription(),
                item.getImage(),
                item.getPrice()
        );
    }
}
