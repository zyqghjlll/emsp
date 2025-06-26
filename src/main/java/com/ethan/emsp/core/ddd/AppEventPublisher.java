package com.ethan.emsp.core.ddd;

public interface AppEventPublisher {
    void publish(AppEvent appEvent);
}
