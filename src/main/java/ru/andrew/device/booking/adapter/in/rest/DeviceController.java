package ru.andrew.device.booking.adapter.in.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.andrew.device.booking.application.in.BookDeviceUseCase;
import ru.andrew.device.booking.application.in.CreateDeviceUseCase;
import ru.andrew.device.booking.application.in.DeleteDeviceUseCase;
import ru.andrew.device.booking.application.in.GetDeviceOrdersUseCase;
import ru.andrew.device.booking.application.in.GetDeviceUseCase;
import ru.andrew.device.booking.application.in.ListDevicesUseCase;
import ru.andrew.device.booking.application.in.ReleaseDeviceUseCase;
import ru.andrew.device.booking.domain.Device;
import ru.andrew.device.booking.domain.Order;

@RestController
@RequestMapping("/devices")
@RequiredArgsConstructor
public class DeviceController {

    private final BookDeviceUseCase bookDeviceUseCase;
    private final ReleaseDeviceUseCase releaseDeviceUseCase;
    private final ListDevicesUseCase listDevicesUseCase;
    private final CreateDeviceUseCase createDeviceUseCase;
    private final DeleteDeviceUseCase deleteDeviceUseCase;
    private final GetDeviceUseCase getDeviceUseCase;
    private final GetDeviceOrdersUseCase getDeviceOrdersUseCase;

    @GetMapping
    public Page<Device> list(@RequestParam(name = "page", defaultValue = "0") Integer page,
                             @RequestParam(name = "size", defaultValue = "10") Integer size) {
        return listDevicesUseCase.list(page, size);
    }

    @GetMapping("/{id}")
    public Device get(@PathVariable("id") String id) {
        return getDeviceUseCase.get(id);
    }

    @PostMapping
    public Device create(@RequestBody CreateDeviceDTO createDeviceDTO) {
        return createDeviceUseCase.create(Device.of(createDeviceDTO.name()));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        deleteDeviceUseCase.delete(id);
    }

    @PostMapping("/{id}/book")
    public Device book(@PathVariable("id") String id) {
        return bookDeviceUseCase.book(id, "user"); //TODO auth
    }

    @DeleteMapping("/{id}/release")
    public Device release(@PathVariable("id") String id) {
        return releaseDeviceUseCase.release(id, "user"); //TODO auth
    }

    @GetMapping("{id}/orders")
    public Page<Order> orders(@PathVariable("id") String id,
                              @RequestParam(name = "page", defaultValue = "0") Integer page,
                              @RequestParam(name = "size", defaultValue = "10") Integer size) {
        return getDeviceOrdersUseCase.orders(id, page, size);
    }

}
