package bachelors.invoice.invoiceservice.service;

import bachelors.invoice.invoiceservice.model.Invoice;
import bachelors.invoice.invoiceservice.model.InvoiceScan;
import bachelors.invoice.invoiceservice.model.User;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public interface InvoiceScanService {

    List<InvoiceScan> findAll();

    Optional<InvoiceScan> findInvoiceScanByInvoiceNumber(String invoiceNumber);

    List<InvoiceScan> findInvoiceScanByDate(OffsetDateTime scanTimestamp);

    InvoiceScan save(OffsetDateTime timestamp, Invoice invoiceNumber, User user);
}
