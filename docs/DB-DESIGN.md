# Database Design Document

This document describes the schema of the charging station information management system.

## Table: db_location

| Column     | Type      | Description           |
|------------|-----------|-----------------------|
| id         | varchar   | Primary key           |
| name       | varchar   | Name of location      |
| address    | varchar   | Address               |
| latitude   | float8    | Latitude coordinate   |
| longitude  | float8    | Longitude coordinate  |
| open_time  | varchar   | Opening time          |
| close_time | varchar   | Closing time          |
| created_at | timestamp | Creation timestamp    |

## Table: db_evse

| Column     | Type    | Description                      |
|------------|---------|----------------------------------|
| id         | varchar | Primary key                      |
| location_id| varchar | Foreign key to db_location       |
| status     | varchar | EVSE status                      |

## Table: db_connector

| Column     | Type    | Description                         |
|------------|---------|-------------------------------------|
| id         | varchar | Primary key                         |
| evse_id    | varchar | Foreign key to db_evse              |
| type       | varchar | Connector type                      |
| voltage    | int8    | Voltage in volts                    |
| amperage   | int8    | Amperage in amps                    |
| power      | float8  | Power in kilowatts                  |
| standard   | varchar | Connector standard (e.g., CCS, GB/T)|

## Table: db_location_view

| Column     | Type        | Description                |
|------------|-------------|----------------------------|
| id         | varchar     | Primary key                |
| location_id| varchar(64) | Refers to location         |
| name       | varchar(128)| Location name              |
| address    | varchar(255)| Address                    |
| latitude   | varchar(32) | Latitude                   |
| longitude  | varchar(32) | Longitude                  |
| open_time  | varchar(32) | Opening time               |
| close_time | varchar(32) | Closing time               |
| created_at | timestamp   | Creation timestamp         |
| updated_at | timestamp   | Last update timestamp      |

## Table: db_evse_view

| Column      | Type        | Description                   |
|-------------|-------------|-------------------------------|
| id          | varchar     | Primary key                   |
| evse_id     | varchar(64) | EVSE identifier               |
| location_id | varchar(64) | Refers to db_location         |
| evse_status | varchar(32) | Current status of EVSE        |
| created_at  | timestamp   | Creation timestamp            |
| updated_at  | timestamp   | Last update timestamp         |

## Table: db_connector_view

| Column             | Type        | Description                          |
|--------------------|-------------|--------------------------------------|
| id                 | varchar     | Primary key                          |
| evse_id            | varchar(64) | Refers to db_evse                    |
| connector_type     | varchar(64) | Type of the connector                |
| connector_voltage  | int4        | Voltage supported by the connector   |
| connector_amperage | int4        | Amperage supported                   |
| connector_power    | float8      | Power capacity in kilowatts          |
| connector_standard | varchar(64) | Standard type                        |
| created_at         | timestamp   | Creation timestamp                   |
| updated_at         | timestamp   | Last update timestamp                |