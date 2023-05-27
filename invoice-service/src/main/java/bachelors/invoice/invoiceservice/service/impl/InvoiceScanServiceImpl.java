package bachelors.invoice.invoiceservice.service.impl;

import bachelors.invoice.invoiceservice.model.Invoice;
import bachelors.invoice.invoiceservice.model.InvoiceScan;
import bachelors.invoice.invoiceservice.model.User;
import bachelors.invoice.invoiceservice.repository.InvoiceScanRepository;
import bachelors.invoice.invoiceservice.service.InvoiceScanService;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceScanServiceImpl implements InvoiceScanService {

    private final InvoiceScanRepository invoiceScanRepository;

    public InvoiceScanServiceImpl(InvoiceScanRepository invoiceScanRepository) {
        this.invoiceScanRepository = invoiceScanRepository;
    }

    @Override
    public List<InvoiceScan> findAll() {
        return this.invoiceScanRepository.findAll();
    }

    @Override
    public Optional<InvoiceScan> findInvoiceScanByInvoiceNumber(String invoiceNumber) {
        return this.invoiceScanRepository.findInvoiceScanByInvoiceNumber(invoiceNumber);
    }

    @Override
    public List<InvoiceScan> findInvoiceScanByDate(OffsetDateTime scanTimestamp) {
        //might be localDate, so return all by the date, time doesn't matter
        return this.invoiceScanRepository.findInvoiceScansByScanTimestamp(scanTimestamp);
    }

    @Override
    public InvoiceScan save(OffsetDateTime timestamp, Invoice invoice, User user) {
        return this.invoiceScanRepository.save(new InvoiceScan(timestamp, invoice, user));
    }
}
