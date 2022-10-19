-- TAGS TABLE --
create table TAGS
(
    ID   INT UNIQUE ,
    NAME VARCHAR,
    constraint TAGS_PK
        primary key (ID)
);

create unique index TAGS_ID_UINDEX
    on TAGS (ID);

create unique index TAGS_NAME_UINDEX
    on TAGS (NAME);

-- GIFT_CERTIFICATES TABLE --

create table gift_certificates
(
    id               serial not null
        constraint gift_certificates_pkey
            primary key,
    name             varchar,
    description      varchar,
    price            double precision,
    duration         integer default 1,
    create_date      timestamp,
    last_update_date timestamp
);

create unique index gift_certificates_name_uindex
    on gift_certificates (name);

-- GIFT_CERTIFICATES_TAGS TABLE --

create table gift_certificates_tags
(
    gc_id  integer
        constraint gift_certificates_tags_gift_certificates_id_fk
            references gift_certificates,
    tag_id integer
        constraint gift_certificates_tags_tags_id_fk
            references tags,
    id     serial not null
        constraint gift_certificates_tags_pk
            primary key
);

create unique index gift_certificates_tags_id_uindex
    on gift_certificates_tags (id);

