package com.ethan.emsp.core.ddd;

public interface AppEventPublisher {
    void publishEvent(AppEvent appEvent);
}
