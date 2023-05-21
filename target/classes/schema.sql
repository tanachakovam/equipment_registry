create table IF NOT EXISTS equipment_lines
(
    id                  BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name                varchar(100),
    country             varchar(50),
    brand               varchar(100),
    is_available_online boolean,
    is_in_instalment    boolean
);

create table IF NOT EXISTS equipment_models
(
    id           BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name         varchar(100),
    colour       varchar(50),
    size         double precision,
    price        double precision,
    is_available boolean,
    line_id      BIGINT REFERENCES equipment_lines (id) ON DELETE RESTRICT
);


create table IF NOT EXISTS pcs
(
    id        integer not null
        constraint pcs_pkey primary key
        constraint pcs_equipment_models_id_fk references equipment_models,
    category  varchar(100),
    processor varchar(100)
);

create table IF NOT EXISTS smartphones
(
    id           integer not null
        constraint phones_pkey primary key
        constraint phones_equipment_models_id_fk references equipment_models,
    memory       int,
    camera_count int
);

create table IF NOT EXISTS vacuum_cleaners
(
    id               integer not null
        constraint cleaners_pkey primary key
        constraint cleaners_equipment_models_id_fk references equipment_models,
    collector_volume double precision,
    regime_count     int
);

create table IF NOT EXISTS tvs
(
    id         integer not null
        constraint tvs_pkey primary key
        constraint tvs_equipment_models_id_fk references equipment_models,
    category   varchar(100),
    technology varchar(100)
);

create table IF NOT EXISTS refrigerators
(
    id         integer not null
        constraint refrigerators_pkey primary key
        constraint refrigerators_equipment_models_id_fk references equipment_models,
    doors      int,
    compressor varchar(100)
);