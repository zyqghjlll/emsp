package com.ethan.emsp.infrastructure.event;

import com.ethan.emsp.core.ddd.AppEvent;
import com.ethan.emsp.core.ddd.AppEventPublisher;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class SpringAppEventPublisher implements AppEventPublisher {

    private ApplicationEventPublisher springPublisher;

    public SpringAppEventPublisher(ApplicationEventPublisher springPublisher) {
        this.springPublisher = springPublisher;
    }

    @Override
    public void publish(AppEvent appEvent) {
        springPublisher.publishEvent(appEvent);
    }
}
