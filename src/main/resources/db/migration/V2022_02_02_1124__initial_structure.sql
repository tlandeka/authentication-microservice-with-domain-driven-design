CREATE TABLE mcuser (
    id                  UUID PRIMARY KEY NOT NULL,
    first_name          VARCHAR(255),
    last_name           VARCHAR(255),
    provider            VARCHAR(50)      NOT NULL,
    email               VARCHAR(255)     NOT NULL
        CONSTRAINT mcuser_email_unique unique,
    concurrency_version BIGINT,
    created             TIMESTAMP        NOT NULL DEFAULT Now(),
    modified            TIMESTAMP
);

CREATE TABLE user_registration (
    id                  BIGSERIAL PRIMARY KEY,
    first_name          VARCHAR(255),
    last_name           VARCHAR(255),
    email               VARCHAR(255)            NOT NULL,
    password            VARCHAR(255)            NOT NULL,
    confirmation_code   VARCHAR(255)            NOT NULL,
    status              VARCHAR(255)            NOT NULL,
    register_date       TIMESTAMP,
    recovery_code       VARCHAR(255),
    recovery_code_expiration_date TIMESTAMP,
    user_id             UUID
        CONSTRAINT user_registration_mcuser_id_fk REFERENCES mcuser,
    concurrency_version BIGINT,
    created             TIMESTAMP DEFAULT now() NOT NULL,
    modified            TIMESTAMP
);

create table session (
    id                  UUID PRIMARY KEY,
    user_id             UUID NOT NULL CONSTRAINT session_mcuser_id_fk REFERENCES mcuser,
    access_token        TEXT NOT NULL,
    expiration_date      TIMESTAMP,
    token_type          TEXT NOT NULL,
    refresh_token       TEXT,
    user_agent          TEXT,
    ip_address          VARCHAR(45),
    last_activity       TIMESTAMP,
    created             TIMESTAMP DEFAULT now() NOT NULL,
    modified            TIMESTAMP
);

CREATE UNIQUE INDEX user_registration_user_id_uindex ON user_registration(user_id);
