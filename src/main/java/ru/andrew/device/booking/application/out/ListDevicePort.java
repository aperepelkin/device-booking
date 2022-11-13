package ru.andrew.device.booking.application.out;

import org.springframework.data.domain.Page;
import ru.andrew.device.booking.domain.Device;

public interface ListDevicePort {
    Page<Device> list(int from, int size);
}
