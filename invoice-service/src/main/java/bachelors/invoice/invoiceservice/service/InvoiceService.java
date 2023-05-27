package bachelors.invoice.invoiceservice.service;

import bachelors.invoice.invoiceservice.model.Invoice;
import bachelors.invoice.invoiceservice.model.dto.InvoiceDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface InvoiceService {

    List<Invoice> findAll();

    Optional<Invoice> findInvoiceByInvoiceNumber(String invoiceNumber);

    Invoice saveInvoice(Invoice invoice);

    List<InvoiceDTO> findAllWithDateScanned();

    List<InvoiceDTO> findAllDueNextWeek();

    Optional<Invoice> findById(String id);

    void deleteById(String id);

    List<String> findAllGroupedByVendor();

    List<String> findAllGroupedByVendorWithAmountPerMonth();

    List<String> findAllWithAmountPerMonth();
}
