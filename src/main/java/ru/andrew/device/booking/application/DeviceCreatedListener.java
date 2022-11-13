package ru.andrew.device.booking.application;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.andrew.device.booking.application.in.UploadDetailsUseCase;

@Component
@RequiredArgsConstructor
public class DeviceCreatedListener {

    private final UploadDetailsUseCase uploadDetailsUseCase;

    @EventListener
    public void handle(DeviceCreatedEvent event) {
        uploadDetailsUseCase.upload(event.device().getId());
    }
}
