package ru.andrew.device.booking.application.in;

import org.springframework.data.domain.Page;
import ru.andrew.device.booking.domain.Device;

public interface ListDevicesUseCase {
    Page<Device> list(Integer page, Integer size);
}
