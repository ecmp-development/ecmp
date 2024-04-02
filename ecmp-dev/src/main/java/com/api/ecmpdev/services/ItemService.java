package com.api.ecmpdev.services;

import com.api.ecmpdev.configs.Messages;
import com.api.ecmpdev.dtos.RequestCreateItem;
import com.api.ecmpdev.dtos.RequestItem;
import com.api.ecmpdev.models.Item;
import com.api.ecmpdev.repositories.ItemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Optional<Item> getItemById(Long id) {
        return Optional.of(itemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Messages.userIdNotFound(id))));
    }

    public List<Item> filter(RequestItem payload) {
        return Optional.of(itemRepository.filterByParams(
                payload.getId(),
                payload.getName(),
                payload.getType(),
                payload.getDescription(),
                payload.getPrice()
        )).orElseThrow(() -> new EntityNotFoundException(Messages.itemFilterError(payload)));
    }

    public HttpStatusCode createItem(RequestCreateItem payload) {
        if (
                !payload.getName().isEmpty()
                        && !payload.getType().name().isEmpty()
                        && !payload.getDescription().isEmpty()
                        && payload.getImage() != null
                        && payload.getPrice() != 0
        ) {
            Item item = Item.builder()
                    .name(payload.getName())
                    .type(payload.getType())
                    .description(payload.getDescription())
                    .image(payload.getImage())
                    .price(payload.getPrice())
                    .build();
            itemRepository.save(item);

            return HttpStatus.OK;
        } else return HttpStatus.BAD_REQUEST;

    }

    public HttpStatusCode deleteItem(Long id) {
        itemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Messages.itemIdNotFound(id)));

        itemRepository.deleteById(id);
        return HttpStatus.OK;
    }
}
