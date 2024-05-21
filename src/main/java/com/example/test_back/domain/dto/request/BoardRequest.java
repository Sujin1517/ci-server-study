package com.example.test_back.domain.dto.request;

import com.example.test_back.domain.entity.Board;

public record BoardRequest(
        String name,
        String text
) {
}
