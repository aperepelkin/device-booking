package ru.andrew.device.booking.application.in;

import ru.andrew.device.booking.domain.Device;

public interface GetDeviceUseCase {
    Device get(String id);
}
