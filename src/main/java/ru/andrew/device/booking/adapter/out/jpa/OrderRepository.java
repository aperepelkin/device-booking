package ru.andrew.device.booking.adapter.out.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, String> {

    Page<OrderEntity> findAllByDeviceId(String id, Pageable pageable);
}
