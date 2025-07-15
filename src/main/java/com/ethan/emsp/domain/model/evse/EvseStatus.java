package com.ethan.emsp.domain.model.evse;

import com.ethan.emsp.core.result.exception.ConflictException;
import com.ethan.emsp.core.result.exception.InvalidArgumentException;

import java.util.Map;
import java.util.Set;

public enum EvseStatus {
    INITIAL, AVAILABLE, BLOCKED, INOPERATIVE, REMOVED;

    private static final Map<EvseStatus, Set<EvseStatus>> TRANSITIONS = Map.of(
            INITIAL, Set.of(AVAILABLE, REMOVED),
            AVAILABLE, Set.of(BLOCKED, INOPERATIVE, REMOVED),
            BLOCKED, Set.of(AVAILABLE, REMOVED),
            INOPERATIVE, Set.of(INITIAL, AVAILABLE, REMOVED),
            REMOVED, Set.of()
    );

    public boolean canTransitionTo(EvseStatus target) {
        return TRANSITIONS.getOrDefault(this, Set.of()).contains(target);
    }

    public static EvseStatus fromCode(String value) {
        for (EvseStatus status : values()) {
            if (status.name().equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new InvalidArgumentException("Unknown EvseStatus: " + value);
    }

    public String getValue() {
        return name();
    }

    public static void selfValidate() {
        // 1. INITIAL -> AVAILABLE
        if (!TRANSITIONS.getOrDefault(INITIAL, Set.of()).contains(AVAILABLE)) {
            throw new ConflictException("INITIAL must be able to transition to AVAILABLE");
        }

        // 2. AVAILABLE <-> BLOCKED
        if (!TRANSITIONS.getOrDefault(AVAILABLE, Set.of()).contains(BLOCKED)) {
            throw new ConflictException("AVAILABLE must be able to transition to BLOCKED");
        }
        if (!TRANSITIONS.getOrDefault(BLOCKED, Set.of()).contains(AVAILABLE)) {
            throw new ConflictException("BLOCKED must be able to transition back to AVAILABLE");
        }

        // 3. AVAILABLE <-> INOPERATIVE
        if (!TRANSITIONS.getOrDefault(AVAILABLE, Set.of()).contains(INOPERATIVE)) {
            throw new ConflictException("AVAILABLE must be able to transition to INOPERATIVE");
        }
        if (!TRANSITIONS.getOrDefault(INOPERATIVE, Set.of()).contains(AVAILABLE)) {
            throw new ConflictException("INOPERATIVE must be able to transition back to AVAILABLE");
        }

        // 4. Any state -> REMOVED
        for (EvseStatus state : values()) {
            if (state != REMOVED && !TRANSITIONS.getOrDefault(state, Set.of()).contains(REMOVED)) {
                throw new ConflictException(state + " must be able to transition to REMOVED");
            }
        }

        // 5. REMOVED is terminal
        if (!TRANSITIONS.getOrDefault(REMOVED, Set.of()).isEmpty()) {
            throw new ConflictException("REMOVED must not have any outgoing transitions");
        }
    }

}
