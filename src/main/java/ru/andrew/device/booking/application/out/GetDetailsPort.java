package ru.andrew.device.booking.application.out;

import java.util.Map;

public interface GetDetailsPort {
    Map<String, String> getDetails(String deviceName);
}
