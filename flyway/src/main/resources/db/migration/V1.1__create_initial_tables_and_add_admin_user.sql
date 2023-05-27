CREATE TABLE "invoice_app_user" (
    "id" bigserial PRIMARY KEY,
    "email" text NOT NULL,
    "password" text NOT NULL
);

CREATE TABLE "customer" (
    "id" bigserial PRIMARY KEY,
    "customer_number" text NOT NULL,
    "customer_name" text,
    "customer_address" text
);

CREATE TABLE "vendor" (
    "id" bigserial PRIMARY KEY,
    "vendor_name" text,
    "vendor_address" text,
    "vendor_email" text,
    "vendor_website" text
);

CREATE TABLE "invoice" (
    "invoice_number" text PRIMARY KEY,
    "date_issued" date NOT NULL,
    "place_issued" text,
    "due_date" date NOT NULL,
    "billing_period_from" date NOT NULL,
    "billing_period_to" date,
    "amount" numeric NOT NULL,
    "currency" text,
    "invoice_debt" numeric,
    "debt_date" date,
    "amount_overpaid" numeric,
    "date_overpaid" date,
    "vat_rate" integer,
    "discount" numeric,
    "is_overpaid" text,
    "customer_id" integer NOT NULL REFERENCES "customer",
    "vendor_id" integer NOT NULL REFERENCES "vendor"
);

CREATE TABLE "invoice_line_items" (
    "id" bigserial PRIMARY KEY,
    "invoice_number" text NOT NULL REFERENCES "invoice",
    "quantity" integer,
    "unit_price" numeric,
    "vat_rate" integer,
    "discount" numeric
);

CREATE TABLE "invoice_scan" (
    "id" bigserial PRIMARY KEY,
    "date_scanned" timestamp with time zone NOT NULL DEFAULT now(),
    "invoice_number" text NOT NULL REFERENCES "invoice",
    "user_id" bigint NOT NULL REFERENCES "invoice_app_user"
);

INSERT INTO "invoice_app_user" ("id", "email", "password")
VALUES ('1', 'admin@admin.com', 'admin');