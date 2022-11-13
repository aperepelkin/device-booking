package ru.andrew.device.booking.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class Order {

    private String id;
    private String deviceId;
    private Instant bookedAt;
    private Instant releasedAt;
    private String author;

    public static Order of(Device device, String author) {
        return new Order(UUID.randomUUID().toString(), device.getId(), Instant.now(), null, author);
    }

    public void release(String author) {
        if (!this.author.equalsIgnoreCase(author)) {
            throw new DomainException("Device booked by another author");
        }
        releasedAt = Instant.now();
    }
}
