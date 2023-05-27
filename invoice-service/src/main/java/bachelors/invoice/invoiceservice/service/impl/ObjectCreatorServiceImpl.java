package bachelors.invoice.invoiceservice.service.impl;

import bachelors.invoice.invoiceservice.model.Customer;
import bachelors.invoice.invoiceservice.model.Invoice;
import bachelors.invoice.invoiceservice.model.InvoiceLineItems;
import bachelors.invoice.invoiceservice.model.Vendor;
import bachelors.invoice.invoiceservice.service.*;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.time.OffsetDateTime;
import java.util.Optional;

@Service
public class ObjectCreatorServiceImpl implements ObjectCreatorService {

    private static final Logger LOG = LoggerFactory.getLogger(ObjectCreatorServiceImpl.class);

    private final CustomerService customerService;
    private final VendorService vendorService;
    private final InvoiceService invoiceService;
    private final InvoiceLineItemsService invoiceLineItemsService;
    private final InvoiceScanService invoiceScanService;
    private final UserService userService;

    public ObjectCreatorServiceImpl(CustomerService customerService,
                                    VendorService vendorService,
                                    InvoiceService invoiceService,
                                    InvoiceLineItemsService invoiceLineItemsService,
                                    InvoiceScanService invoiceScanService,
                                    UserService userService) {
        this.customerService = customerService;
        this.vendorService = vendorService;
        this.invoiceService = invoiceService;
        this.invoiceLineItemsService = invoiceLineItemsService;
        this.invoiceScanService = invoiceScanService;
        this.userService = userService;
    }

    @Override
    @Transactional
    public void saveObjects(Customer customer, Vendor vendor, Invoice invoice, InvoiceLineItems invoiceLineItems) {
        Optional<Invoice> invoiceOpt = checkIfInvoiceWithInvoiceNumberExists(invoice.getInvoiceNumber());
        if (invoiceOpt.isPresent()) {
            LOG.info("Already scanned invoice.");
            return;
        }
        saveCustomer(customer);
        saveVendor(vendor);
        saveInvoice(invoice, customer, vendor);
        saveInvoiceLineItems(invoiceLineItems, invoice);
        saveInvoiceScan(invoice);
    }

    private void saveCustomer(Customer customer) {
        customerService.saveCustomer(customer);
    }

    private void saveVendor(Vendor vendor) {
        vendorService.saveVendor(vendor);
    }

    private void saveInvoice(Invoice invoice, Customer customer, Vendor vendor) {
        invoice.setVendorId(vendor);
        invoice.setCustomerId(customer);
        invoiceService.saveInvoice(invoice);
    }

    private void saveInvoiceLineItems(InvoiceLineItems invoiceLineItems, Invoice invoice) {
        invoiceLineItems.setInvoiceNumber(invoice);
        invoiceLineItemsService.saveInvoiceLineItems(invoiceLineItems);
    }

    private void saveInvoiceScan(Invoice invoice) {
        invoiceScanService.save(OffsetDateTime.now(), invoice, userService.findById().get());
    }

    private Optional<Invoice> checkIfInvoiceWithInvoiceNumberExists(final String invoiceNumber) {
        return invoiceService.findInvoiceByInvoiceNumber(invoiceNumber);
    }
}
