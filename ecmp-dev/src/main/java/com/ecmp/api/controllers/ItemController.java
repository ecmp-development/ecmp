package com.ecmp.api.controllers;

import com.ecmp.api.dtos.RequestCreateItem;
import com.ecmp.api.dtos.FilterItem;
import com.ecmp.api.dtos.ResponseItem;
import com.ecmp.api.models.Item;
import com.ecmp.api.services.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/update")
    public ResponseEntity<String> updateItem(@RequestBody Item payload) {
        return itemService.updateItem(payload);
    }

    @DeleteMapping("/remove/{id}")
    public HttpStatusCode deleteItem(@PathVariable Long id) {
        return itemService.deleteItem(id);
    }
}
