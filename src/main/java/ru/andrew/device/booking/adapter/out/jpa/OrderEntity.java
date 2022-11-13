package ru.andrew.device.booking.adapter.out.jpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class OrderEntity {

    @Id
    private String id;
    private String deviceId;
    private Instant bookedAt;
    private Instant releasedAt;
    private String author;

}
