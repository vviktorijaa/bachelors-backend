package bachelors.invoice.invoiceservice.model.dto;

import org.hibernate.annotations.NamedNativeQuery;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

public record InvoiceDTO(String vendorName,
                         String invoiceNumber,
                         LocalDate dueDate,
                         BigDecimal totalAmount,
                         OffsetDateTime scanTimestamp) {
}
