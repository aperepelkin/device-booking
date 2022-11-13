package ru.andrew.device.booking.adapter.out.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import ru.andrew.device.booking.application.out.GetDeviceOrdersPort;
import ru.andrew.device.booking.application.out.GetDevicePort;
import ru.andrew.device.booking.application.out.ListDevicePort;
import ru.andrew.device.booking.application.out.SaveDevicePort;
import ru.andrew.device.booking.domain.Device;
import ru.andrew.device.booking.domain.Order;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JpaDevicePort implements
        GetDevicePort,
        SaveDevicePort,
        ListDevicePort,
        GetDeviceOrdersPort {

    private final DeviceRepository deviceRepository;
    private final OrderRepository orderRepository;
    private final DomainMapper mapper;

    @Override
    public Optional<Device> findById(String id) {
        return deviceRepository.findById(id)
                .map(mapper::map);
    }

    @Override
    public Device save(Device device) {
        return mapper.map(deviceRepository.save(mapper.map(device)));
    }

    @Override
    public Page<Device> list(int page, int size) {
        return deviceRepository.findAllByDeletedFalse(PageRequest.of(page, size))
                .map(mapper::map);
    }

    @Override
    public Page<Order> orders(String id, Integer page, Integer size) {
        return orderRepository.findAllByDeviceId(id, PageRequest.of(page, size))
                .map(mapper::map);
    }
}
