package bachelors.invoice.invoiceservice.web;

import bachelors.invoice.invoiceservice.model.dto.InvoiceDTO;
import bachelors.invoice.invoiceservice.service.InvoiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/invoices")
    public List<InvoiceDTO> getInvoices() {
        return this.invoiceService.findAllWithDateScanned();
    }

    @GetMapping("/dueNextWeek")
    public List<InvoiceDTO> getAllDueNextWeek() {
        return this.invoiceService.findAllDueNextWeek();
    }

    @GetMapping("/groupedByVendor")
    public List<String> getAllGroupedByVendor() {
        return this.invoiceService.findAllGroupedByVendor();
    }

    @GetMapping("/groupedByVendorTotalAmountPerMonth")
    public List<String> getAllGroupedByVendorWithAmountPerMonth() {
        return this.invoiceService.findAllGroupedByVendorWithAmountPerMonth();
    }

    @GetMapping("/totalAmountPerMonth")
    public List<String> getAllWithTotalAmountPerMonth() {
        return this.invoiceService.findAllWithAmountPerMonth();
    }

    @DeleteMapping(value = "/invoices/{id}")
    public ResponseEntity deleteById(@PathVariable String id) {
        this.invoiceService.deleteById(id);
        if (this.invoiceService.findById(id).isEmpty()) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
