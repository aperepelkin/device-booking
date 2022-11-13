package ru.andrew.device.booking.application;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import ru.andrew.device.booking.application.in.BookDeviceUseCase;
import ru.andrew.device.booking.application.in.CreateDeviceUseCase;
import ru.andrew.device.booking.application.in.DeleteDeviceUseCase;
import ru.andrew.device.booking.application.in.GetDeviceOrdersUseCase;
import ru.andrew.device.booking.application.in.GetDeviceUseCase;
import ru.andrew.device.booking.application.in.ListDevicesUseCase;
import ru.andrew.device.booking.application.in.ReleaseDeviceUseCase;
import ru.andrew.device.booking.application.out.GetDeviceOrdersPort;
import ru.andrew.device.booking.application.out.GetDevicePort;
import ru.andrew.device.booking.application.out.ListDevicePort;
import ru.andrew.device.booking.application.out.SaveDevicePort;
import ru.andrew.device.booking.domain.Device;
import ru.andrew.device.booking.domain.Order;

@Component
@RequiredArgsConstructor
public class DeviceCases implements
        BookDeviceUseCase,
        ListDevicesUseCase,
        CreateDeviceUseCase,
        DeleteDeviceUseCase,
        GetDeviceUseCase,
        ReleaseDeviceUseCase,
        GetDeviceOrdersUseCase {

    private final GetDevicePort getDevicePort;
    private final SaveDevicePort saveDevicePort;
    private final ListDevicePort listDevicePort;
    private final GetDeviceOrdersPort getDeviceOrdersPort;

    private final ApplicationEventPublisher publisher;

    @Override
    public Device book(String deviceId, String user) {
        Device device = ensureGetDevice(deviceId);
        device.book(user);

        return saveDevicePort.save(device);
    }

    @Override
    public Device release(String id, String user) {
        Device device = ensureGetDevice(id);
        device.release(user);

        return saveDevicePort.save(device);
    }

    @Override
    public Page<Device> list(Integer page, Integer size) {
        return listDevicePort.list(page, size);
    }

    @Override
    public Device create(Device device) {

        var result = saveDevicePort.save(device);
        publisher.publishEvent(new DeviceCreatedEvent(device));
        return result;
    }

    @Override
    public void delete(String id) {
        var device = ensureGetDevice(id);
        device.delete();
        saveDevicePort.save(device);
    }

    @Override
    public Device get(String id) {
        return ensureGetDevice(id);
    }

    private Device ensureGetDevice(String id) {
        return getDevicePort.findById(id)
                .orElseThrow(() -> new NotFoundException("Device not found"));
    }

    @Override
    public Page<Order> orders(String id, Integer page, Integer size) {
        return getDeviceOrdersPort.orders(id, page, size);
    }
}
