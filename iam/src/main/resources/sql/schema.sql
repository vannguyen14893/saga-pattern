create schema iam;
use iam;
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
INSERT INTO oauth2_registered_client (id, client_id, client_id_issued_at, client_secret, client_secret_expires_at, client_name, client_authentication_methods, authorization_grant_types, redirect_uris, post_logout_redirect_uris, scopes, client_settings, token_settings) VALUES ('7c98dd88-c9e0-47b5-b59e-68d371421822', 'saga', '2025-04-29 19:41:22', '{bcrypt}$2a$12$zkaV0vFO8Tn3FV.AvzUns.na..R3vr/gyKVcLOvhd.lVYTrA6UpTS', null, '7c98dd88-c9e0-47b5-b59e-68d371421822', 'client_secret_post', 'refresh_token,custom_password,authorization_code', 'https://oidcdebugger.com/debug', 'http://localhost:3000', 'openid,profile', '{"@class":"java.util.Collections$UnmodifiableMap","settings.client.require-proof-key":false,"settings.client.require-authorization-consent":false}', '{"@class":"java.util.Collections$UnmodifiableMap","settings.token.reuse-refresh-tokens":true,"settings.token.x509-certificate-bound-access-tokens":false,"settings.token.id-token-signature-algorithm":["org.springframework.security.oauth2.jose.jws.SignatureAlgorithm","RS256"],"settings.token.access-token-time-to-live":["java.time.Duration",3600.000000000],"settings.token.access-token-format":{"@class":"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat","value":"self-contained"},"settings.token.refresh-token-time-to-live":["java.time.Duration",10800.000000000],"settings.token.authorization-code-time-to-live":["java.time.Duration",600.000000000],"settings.token.device-code-time-to-live":["java.time.Duration",300.000000000]}');
INSERT INTO user (id, avatar, count_login_attempt, email, full_name, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled, language, last_failed_login_date, multifactor_authentication_type, otp, otp_expire_date, password, phone, secret) VALUES (1, null, 0, 'ducvan14894@gmail.com', 'ndvan', true, true, true, true, 'vi', null, 'SMS', 463848, '2025-05-07 14:15:31.845333', '{bcrypt}$2a$12$QkedbrHqWelqelJC5lIpFuRlhGU3yO8bAXoSPZZ3WvmS4rfvk9.Xu', '1234567890', null);
INSERT INTO user (id, avatar, count_login_attempt, email, full_name, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled, language, last_failed_login_date, multifactor_authentication_type, otp, otp_expire_date, password, phone, secret) VALUES (3, null, 0, 'ducvan14894@gmail.com', 'ndvan', true, true, true, true, 'vi', null, 'GOOGLE_AUTH', null, null, '{bcrypt}$2a$12$h1Y47o7mLap/dMlzvoP0.eR4TnPy.4Kq1AvtGhrVD4kodqvmmK8Yi', '1234567891', null);
