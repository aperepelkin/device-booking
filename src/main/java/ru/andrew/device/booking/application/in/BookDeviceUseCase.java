package ru.andrew.device.booking.application.in;

import ru.andrew.device.booking.domain.Device;

public interface BookDeviceUseCase {
    Device book(String deviceId, String user);
}
