package ru.andrew.device.booking.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.andrew.device.booking.application.in.UploadDetailsUseCase;
import ru.andrew.device.booking.application.out.GetDetailsPort;
import ru.andrew.device.booking.application.out.GetDevicePort;
import ru.andrew.device.booking.application.out.SaveDevicePort;
import ru.andrew.device.booking.application.out.TaskSchedulerPort;
import ru.andrew.device.booking.domain.DomainException;

@Component
@RequiredArgsConstructor
public class UploadDetailsUseCaseImpl implements UploadDetailsUseCase {

    private final GetDevicePort getDevicePort;
    private final SaveDevicePort saveDevicePort;
    private final GetDetailsPort getDetailsPort;
    private final TaskSchedulerPort taskSchedulerPort;

    @Override
    public void upload(String id) {
        try {
            var device = getDevicePort.findById(id).orElseThrow(() -> new DomainException("Device not found"));
            var details = getDetailsPort.getDetails(device.getName());
            device.setDetails(details);
            saveDevicePort.save(device);
        } catch (DetailsUnavailableException e) {
            taskSchedulerPort.schedule(id, 3600);
        }
    }
}
