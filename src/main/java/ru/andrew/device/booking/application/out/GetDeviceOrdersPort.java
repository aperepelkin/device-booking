package ru.andrew.device.booking.application.out;

import org.springframework.data.domain.Page;
import ru.andrew.device.booking.domain.Order;

public interface GetDeviceOrdersPort {
    Page<Order> orders(String id, Integer page, Integer size);
}
