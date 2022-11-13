package ru.andrew.device.booking.application.out;

public interface TaskSchedulerPort {
    void schedule(String id, long seconds);
}
