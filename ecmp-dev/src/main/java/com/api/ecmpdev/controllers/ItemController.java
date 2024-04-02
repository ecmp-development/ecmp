package com.api.ecmpdev.controllers;

import com.api.ecmpdev.dtos.RequestCreateItem;
import com.api.ecmpdev.dtos.RequestItem;
import com.api.ecmpdev.models.Item;
import com.api.ecmpdev.services.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/items")
public class ItemController {

    private final ItemService itemService;

    @GetMapping("")
    public List<Item> getAll() {
        return itemService.getAllItems();
    }

    @GetMapping("/filter")
    public List<Item> getFilter(@RequestBody RequestItem payload) {
        return itemService.filter(payload);
    }

    @PostMapping("/create")
    public HttpStatusCode createItem(@RequestBody RequestCreateItem payload) {
        return itemService.createItem(payload);
    }
}
