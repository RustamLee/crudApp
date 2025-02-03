package com.rustamlee.focuslist.domain.dto;

/**
 * A record that represents an error response in the application.
 * It contains the HTTP status code, an error message, and additional details.
 *
 * @param status  the HTTP status code
 * @param message the error message describing the issue
 * @param details additional details about the error (e.g., request path)
 */

public record ErrorResponce(
        int status,
        String message,
        String details
) {
}
