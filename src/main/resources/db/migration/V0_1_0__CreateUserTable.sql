CREATE TABLE IF NOT EXISTS "users" (
    id bigserial PRIMARY KEY,
    name VARCHAR(512) NOT NULL,
    dni CHAR(9) NOT NULL UNIQUE
);
CREATE INDEX user_dni ON "users" USING HASH ("dni");