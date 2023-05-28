package bachelors.invoice.invoiceservice.repository;

import bachelors.invoice.invoiceservice.model.Invoice;
import bachelors.invoice.invoiceservice.model.dto.InvoiceDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface InvoiceRepository extends JpaRepository<Invoice, String> {

    List<Invoice> findAll();

    Optional<Invoice> findInvoiceByInvoiceNumber(String invoiceNumber);
    @Query(value = "SELECT new bachelors.invoice.invoiceservice.model.dto.InvoiceDTO(vendor.vendorName, invoice.invoiceNumber, invoice.dueDate, invoice.totalAmount, invoiceScan.scanTimestamp) " +
                   "FROM Invoice invoice " +
                   "JOIN InvoiceScan invoiceScan ON invoice.invoiceNumber = invoiceScan.invoiceNumber JOIN Vendor vendor ON invoice.vendorId = vendor.id")
    List<InvoiceDTO> findAllWithDateScanned();

    @Query(value = "SELECT new bachelors.invoice.invoiceservice.model.dto.InvoiceDTO(vendor.vendorName, invoice.invoiceNumber, invoice.dueDate, invoice.totalAmount, invoiceScan.scanTimestamp) FROM Invoice invoice JOIN InvoiceScan invoiceScan ON invoice.invoiceNumber = invoiceScan.invoiceNumber JOIN Vendor vendor ON invoice.vendorId = vendor.id WHERE FUNCTION('DATEDIFF', DAY, CURRENT_DATE, invoice.dueDate) BETWEEN 0 AND 7")
    List<InvoiceDTO> findAllDueNextWeek();

    @Query(value = "SELECT vendor.vendorEmail, count(invoice.invoiceNumber) as numberOccurences FROM Invoice invoice JOIN Vendor vendor ON invoice.vendorId = vendor.id GROUP BY vendor.vendorEmail")
    List<String> findAllGroupedByVendor();

    @Query(value = "SELECT vendor.vendorEmail, count(invoice.invoiceNumber) as numberOccurences, sum(invoice.totalAmount) AS totalAmount, FUNCTION('date_part', 'MONTH', scan.scanTimestamp) as month FROM Invoice invoice JOIN Vendor vendor ON invoice.vendorId = vendor.id join InvoiceScan as scan on invoice.invoiceNumber = scan.invoiceNumber WHERE FUNCTION('date_part', 'MONTH', scan.scanTimestamp) = FUNCTION('date_part', 'MONTH', CURRENT_DATE) GROUP BY vendor.vendorEmail, FUNCTION('date_part', 'MONTH', scan.scanTimestamp)")
    List<String> findAllGroupedByVendorWithAmountPerMonth();

    @Query(value = "SELECT count(invoice.invoiceNumber) as numberOccurences, sum(invoice.totalAmount) AS totalAmount, FUNCTION('date_part', 'MONTH', scan.scanTimestamp) as month FROM Invoice invoice join InvoiceScan as scan on invoice.invoiceNumber = scan.invoiceNumber WHERE FUNCTION('date_part', 'MONTH', scan.scanTimestamp) = FUNCTION('date_part', 'MONTH', CURRENT_DATE) GROUP BY FUNCTION('date_part', 'MONTH', scan.scanTimestamp)")
    List<String> findAllWithAmountPerMonth();
}
