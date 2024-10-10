package com.app.ecom.dto;

public record ProductRequest(
        String id,
        String name,
        double price,
        int amount,
        Long categoryId
) {
}
