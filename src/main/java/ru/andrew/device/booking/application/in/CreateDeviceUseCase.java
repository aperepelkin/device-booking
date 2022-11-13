package ru.andrew.device.booking.application.in;

import ru.andrew.device.booking.domain.Device;

public interface CreateDeviceUseCase {
    Device create(Device device);
}
