package com.api.ecmpdev.controllers;

import com.api.ecmpdev.dtos.RequestCreateItem;
import com.api.ecmpdev.dtos.FilterItem;
import com.api.ecmpdev.dtos.ResponseItem;
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
    public List<ResponseItem> getAll() {
        return itemService.getAllItems();
    }

    @GetMapping("/{id}")
    public Optional<ResponseItem> getItemById(@PathVariable Long id) {
        return itemService.getItemById(id);
    }

    @GetMapping("/filter")
    public List<ResponseItem> getFilter(@RequestBody FilterItem payload) {
        return itemService.filter(payload);
    }

    @PostMapping("/create")
    public HttpStatusCode createItem(@RequestBody RequestCreateItem payload) {
        return itemService.createItem(payload);
    }

    @DeleteMapping("/remove/{id}")
    public HttpStatusCode deleteItem(@PathVariable Long id) {
        return itemService.deleteItem(id);
    }
}
