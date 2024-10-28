package com.ecmp.api.services;

import com.ecmp.api.configs.exceptions.ItemIdNotFoundException;
import com.ecmp.api.dtos.FilterItem;
import com.ecmp.api.dtos.RequestCreateItem;
import com.ecmp.api.dtos.ResponseItem;
import com.ecmp.api.dtos.mappers.ResponseItemMapper;
import com.ecmp.api.models.Item;
import com.ecmp.api.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final ResponseItemMapper responseItemMapper;

    @Autowired
    public ItemService(
            ItemRepository itemRepository,
            ResponseItemMapper responseItemMapper) {
        this.itemRepository = itemRepository;
        this.responseItemMapper = responseItemMapper;
    }

    public List<ResponseItem> getAllItems() {
        return itemRepository.findAll()
                .stream()
                .map(responseItemMapper)
                .collect(Collectors.toList());
    }

    public Optional<ResponseItem> getItemById(Long id) {
        return Optional.of(itemRepository.findById(id)
                .map(responseItemMapper)
                .orElseThrow(() -> new ItemIdNotFoundException(id)));
    }

    public List<ResponseItem> filter(FilterItem payload) {
        return itemRepository.filterByParams
                        (
                                payload.getId(),
                                payload.getName(),
                                payload.getType(),
                                payload.getDescription(),
                                payload.getPrice()
                        )
                .stream()
                .map(responseItemMapper).collect(Collectors.toList());
    }

    public HttpStatusCode createItem(RequestCreateItem payload) {
        if (
                !payload.getName().isEmpty()
                        && !payload.getType().name().isEmpty()
                        && !payload.getDescription().isEmpty()
                        && !payload.getImage().isEmpty()
                        && !(payload.getPrice() <= 0.00)
        ) {
            Item item = Item.builder()
                    .name(payload.getName())
                    .type(payload.getType())
                    .description(payload.getDescription())
                    .image(payload.getImage().getBytes())
                    .price(payload.getPrice())
                    .build();
            itemRepository.save(item);

            return HttpStatus.OK;
        } else return HttpStatus.BAD_REQUEST;

    }

    public ResponseEntity<String> updateItem(Item payload) {
        

        return new ResponseEntity<>(payload.toString(), HttpStatus.OK);
    }

    public HttpStatusCode deleteItem(Long id) {
        itemRepository.delete(
                itemRepository.findById(id)
                        .orElseThrow(() -> new ItemIdNotFoundException(id))
        );
        return HttpStatus.OK;
    }
}
