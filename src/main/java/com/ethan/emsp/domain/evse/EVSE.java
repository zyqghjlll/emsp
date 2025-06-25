package com.ethan.emsp.domain.evse;

import com.ethan.emsp.core.ddd.Entity;
import com.ethan.emsp.domain.connector.Connector;

import java.util.*;

public class EVSE implements Entity {

    private List<Connector> connectors;

    @Override
    public String getId() {
        return null;
    }
}
