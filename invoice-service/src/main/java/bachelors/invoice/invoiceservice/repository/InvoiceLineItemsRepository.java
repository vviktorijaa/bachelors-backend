package bachelors.invoice.invoiceservice.repository;

import bachelors.invoice.invoiceservice.model.InvoiceLineItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceLineItemsRepository extends JpaRepository<InvoiceLineItems, Long> {
}
