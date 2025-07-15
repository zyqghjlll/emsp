package com.ethan.emsp.domain.model.location;


public interface LocationCmdRepository {
    void save(Location location);

    Location getById(LocationId id);

    void update(Location location);

    boolean existsByNameAndAddress(String name, String address);
}
