package ru.andrew.device.booking.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class Device {

    private String id;
    private Integer version;
    private Instant createdAt;
    private Instant updatedAt;

    private String name;
    private Boolean available;
    private Boolean deleted;
    private Order lastOrder;
    private Map<String, String> details;

    public static Device of(String name) {
        return new Device(UUID.randomUUID().toString(),
                0,
                null,
                null,
                name,
                true,
                false,
                null,
                Map.of());
    }

    public Order book(String author) {
        if (available) {
            available = false;
            lastOrder = Order.of(this, author);
            return lastOrder;
        } else {
            throw new DomainException("Device is busy now");
        }
    }

    public Order release(String author) {
        if (!available) {
            available = true;
            lastOrder.release(author);
        }
        return lastOrder;
    }

    public void delete() {
        deleted = true;
    }

    public void setDetails(Map<String, String> details) {
        this.details = details;
    }
}
