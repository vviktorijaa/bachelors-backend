package bachelors.invoice.invoiceservice.repository;

import bachelors.invoice.invoiceservice.model.InvoiceScan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public interface InvoiceScanRepository extends JpaRepository<InvoiceScan, Long> {

    List<InvoiceScan> findAll();

    Optional<InvoiceScan> findInvoiceScanByInvoiceNumber(String invoiceNumber);

    List<InvoiceScan> findInvoiceScansByScanTimestamp(OffsetDateTime scanTimestamp);
}
