package ru.andrew.device.booking.adapter;

import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;
import ru.andrew.device.booking.application.in.UploadDetailsUseCase;
import ru.andrew.device.booking.application.out.TaskSchedulerPort;

@Component
public class SpringSchedulerPort implements TaskSchedulerPort {

    private final TaskScheduler taskScheduler;
    private final UploadDetailsUseCase uploadDetailsUseCase;

    public SpringSchedulerPort(
            TaskScheduler taskScheduler,
            @Lazy UploadDetailsUseCase uploadDetailsUseCase) {
        this.taskScheduler = taskScheduler;
        this.uploadDetailsUseCase = uploadDetailsUseCase;
    }

    @Override
    public void schedule(String id, long seconds) {
        taskScheduler.scheduleWithFixedDelay(() -> uploadDetailsUseCase.upload(id), seconds * 1000);
    }
}
