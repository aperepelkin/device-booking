package ru.andrew.device.booking.adapter.out.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<DeviceEntity, String> {
    Page<DeviceEntity> findAllByDeletedFalse(Pageable pageable);
}
