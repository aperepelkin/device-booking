package ru.andrew.device.booking.application.out;

import ru.andrew.device.booking.domain.Device;

import java.util.Optional;

public interface GetDevicePort {
    Optional<Device> findById(String id);
}
