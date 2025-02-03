package com.rustamlee.focuslist.domain.dto;

public record ErrorResponce(
        int status,
        String message,
        String details
) {
}
