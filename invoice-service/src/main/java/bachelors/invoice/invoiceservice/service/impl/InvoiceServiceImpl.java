package bachelors.invoice.invoiceservice.service.impl;

import bachelors.invoice.invoiceservice.model.Invoice;
import bachelors.invoice.invoiceservice.model.dto.InvoiceDTO;
import bachelors.invoice.invoiceservice.repository.InvoiceRepository;
import bachelors.invoice.invoiceservice.service.InvoiceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public List<Invoice> findAll() {
        return this.invoiceRepository.findAll();
    }

    @Override
    public Optional<Invoice> findInvoiceByInvoiceNumber(String invoiceNumber) {
        return this.invoiceRepository.findInvoiceByInvoiceNumber(invoiceNumber);
    }
    @Override
    public Invoice saveInvoice(Invoice invoice) {
        return this.invoiceRepository.save(invoice);
    }

    @Override
    public List<InvoiceDTO> findAllWithDateScanned() {
        return invoiceRepository.findAllWithDateScanned();
    }

    @Override
    public List<InvoiceDTO> findAllDueNextWeek() {
        return this.invoiceRepository.findAllDueNextWeek();
    }

    @Override
    public Optional<Invoice> findById(String id) {
        return this.invoiceRepository.findById(id);
    }

    @Override
    public void deleteById(String id) {
        this.invoiceRepository.deleteById(id);
    }

    @Override
    public List<String> findAllGroupedByVendor() {
        return this.invoiceRepository.findAllGroupedByVendor();
    }

    @Override
    public List<String> findAllGroupedByVendorWithAmountPerMonth() {
        return this.invoiceRepository.findAllGroupedByVendorWithAmountPerMonth();
    }

    @Override
    public List<String> findAllWithAmountPerMonth() {
        return this.invoiceRepository.totalAmountPerMonth();
    }
}
