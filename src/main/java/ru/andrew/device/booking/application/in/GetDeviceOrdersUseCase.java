package ru.andrew.device.booking.application.in;

import org.springframework.data.domain.Page;
import ru.andrew.device.booking.domain.Order;

public interface GetDeviceOrdersUseCase {
    Page<Order> orders(String id, Integer page, Integer size);
}
