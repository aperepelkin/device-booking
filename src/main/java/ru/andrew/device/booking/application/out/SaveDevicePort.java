package ru.andrew.device.booking.application.out;

import ru.andrew.device.booking.domain.Device;

public interface SaveDevicePort {
    Device save(Device device);
}
