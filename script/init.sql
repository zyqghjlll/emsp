-- public.db_location definition

-- Drop table

-- DROP TABLE public.db_location;

CREATE TABLE public.db_location
(
    id         varchar NOT NULL,
    "name"     varchar NULL,
    address    varchar NULL,
    latitude   float8 NULL,
    longitude  float8 NULL,
    open_time  varchar NULL,
    close_time varchar NULL,
    created_at timestamp NULL,
    CONSTRAINT db_location_pk PRIMARY KEY (id)
);

-- public.db_evse definition

-- Drop table

-- DROP TABLE public.db_evse;

CREATE TABLE public.db_evse
(
    id          varchar NOT NULL,
    location_id varchar NULL,
    status      varchar NULL,
    CONSTRAINT db_evse_pk PRIMARY KEY (id)
);

-- public.db_connector definition

-- Drop table

-- DROP TABLE public.db_connector;

CREATE TABLE public.db_connector
(
    id       varchar NOT NULL,
    evse_id  varchar NULL,
    "type"   varchar NULL,
    voltage  int8 NULL,
    amperage int8 NULL,
    power    float8 NULL,
    standard varchar NULL,
    CONSTRAINT db_connector_pk PRIMARY KEY (id)
);

-- public.db_location_update_main_view definition

-- Drop table

-- DROP TABLE public.db_location_update_main_view;

CREATE TABLE public.db_location_update_main_view
(
    id           varchar NOT NULL,
    location_id  varchar NULL,
    last_updated timestamp NULL,
    CONSTRAINT db_location_update_view_pk PRIMARY KEY (id)
);

-- public.db_location_update_detail_view definition

-- Drop table

-- DROP TABLE public.db_location_update_detail_view;

CREATE TABLE public.db_location_update_detail_view
(
    id                 varchar     NOT NULL,
    location_id        varchar(64) NOT NULL,
    location_name      varchar(255) NULL,
    location_address   varchar(255) NULL,
    latitude           varchar(50) NULL,
    longitude          varchar(50) NULL,
    open_time          varchar(50) NULL,
    close_time         varchar(50) NULL,
    evse_id            varchar(64) NULL,
    evse_status        varchar(32) NULL,
    connector_type     varchar(32) NULL,
    connector_voltage  int4 NULL,
    connector_amperage int4 NULL,
    connector_power    float8 NULL,
    connector_standard varchar(32) NULL,
    main_id            varchar NULL,
    CONSTRAINT location_evse_view_pkey PRIMARY KEY (id)
);