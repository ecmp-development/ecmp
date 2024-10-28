package com.ecmp.api.dtos;

import com.ecmp.api.enums.Types;

public record ResponseItem (
        String name,
        Types type,
        int quantity,
        String description,
        byte[] image,
        double price
){

}
