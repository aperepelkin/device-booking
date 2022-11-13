package ru.andrew.device.booking.application.in;

import ru.andrew.device.booking.domain.Device;

public interface ReleaseDeviceUseCase {
    Device release(String id, String user);
}
