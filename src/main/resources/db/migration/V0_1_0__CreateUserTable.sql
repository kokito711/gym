CREATE TABLE IF NOT EXISTS "user" (
    id bigserial PRIMARY KEY,
    name VARCHAR(512) NOT NULL,
    dni CHAR(9) NOT NULL UNIQUE
);
CREATE INDEX user_dni ON "user" USING HASH ("dni");