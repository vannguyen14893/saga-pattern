create table oauth2_authorization
(
    id                            varchar(255)  not null
        primary key,
    access_token_expires_at       datetime(6)   null,
    access_token_issued_at        datetime(6)   null,
    access_token_metadata         longtext      null,
    access_token_scopes           varchar(1000) null,
    access_token_type             varchar(255)  null,
    access_token_value            longtext      null,
    attributes                    longtext      null,
    authorization_code_expires_at datetime(6)   null,
    authorization_code_issued_at  datetime(6)   null,
    authorization_code_metadata   longtext      null,
    authorization_code_value      longtext      null,
    authorization_grant_type      varchar(255)  null,
    authorized_scopes             varchar(1000) null,
    device_code_expires_at        datetime(6)   null,
    device_code_issued_at         datetime(6)   null,
    device_code_metadata          longtext      null,
    device_code_value             longtext      null,
    oidc_id_token_claims          longtext      null,
    oidc_id_token_expires_at      datetime(6)   null,
    oidc_id_token_issued_at       datetime(6)   null,
    oidc_id_token_metadata        longtext      null,
    oidc_id_token_value           longtext      null,
    principal_name                varchar(255)  null,
    refresh_token_expires_at      datetime(6)   null,
    refresh_token_issued_at       datetime(6)   null,
    refresh_token_metadata        longtext      null,
    refresh_token_value           longtext      null,
    registered_client_id          varchar(255)  null,
    state                         varchar(500)  null,
    user_code_expires_at          datetime(6)   null,
    user_code_issued_at           datetime(6)   null,
    user_code_metadata            longtext      null,
    user_code_value               longtext      null
);

create table oauth2_authorization_consent
(
    principal_name       varchar(255)  not null,
    registered_client_id varchar(255)  not null,
    authorities          varchar(1000) null,
    primary key (principal_name, registered_client_id)
);

create table oauth2_registered_client
(
    id                            varchar(100)  not null
        primary key,
    client_id                     varchar(100)  not null,
    client_id_issued_at           timestamp     null,
    client_secret                 varchar(200)  not null,
    client_secret_expires_at      timestamp     null,
    client_name                   varchar(200)  not null,
    client_authentication_methods varchar(1000) null,
    authorization_grant_types     varchar(1000) null,
    redirect_uris                 varchar(2000) null,
    post_logout_redirect_uris     varchar(2000) null,
    scopes                        varchar(2000) null,
    client_settings               varchar(2000) null,
    token_settings                varchar(2000) null
);

