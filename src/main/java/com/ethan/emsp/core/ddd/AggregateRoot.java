package com.ethan.emsp.core.ddd;

import java.io.Serializable;

public interface AggregateRoot<T> extends Serializable {
    T getId();
}
