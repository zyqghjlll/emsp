package com.ethan.emsp.core.ddd;

import java.io.Serializable;

public interface AggregateRoot extends Serializable {
    String getId();
}
