package com.api.ecmpdev.dtos;

import com.api.ecmpdev.enums.Types;

public record ResponseItem (
        String name,
        Types type,
        int quantity,
        String description,
        byte[] image,
        double price
){

}
