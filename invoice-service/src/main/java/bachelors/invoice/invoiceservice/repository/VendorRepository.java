package bachelors.invoice.invoiceservice.repository;

import bachelors.invoice.invoiceservice.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
}
