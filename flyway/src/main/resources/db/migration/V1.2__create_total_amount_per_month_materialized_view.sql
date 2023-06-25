CREATE MATERIALIZED VIEW "total_amount_per_month" AS
SELECT month_, count(invoice.invoice_number) as number_invoices, SUM(invoice.amount) as total_amount
FROM generate_series(1, 12, 1) AS month_
JOIN "invoice_scan" AS scan ON extract('month' FROM date_scanned) = month_
JOIN "invoice" AS invoice ON scan.invoice_number = invoice.invoice_number
GROUP BY month_