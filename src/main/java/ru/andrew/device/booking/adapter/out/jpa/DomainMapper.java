package ru.andrew.device.booking.adapter.out.jpa;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.andrew.device.booking.domain.Device;
import ru.andrew.device.booking.domain.Order;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DomainMapper {

    Device map(DeviceEntity entity);

    Order map(OrderEntity entity);

    DeviceEntity map(Device device);

    OrderEntity map(Order order);


}
