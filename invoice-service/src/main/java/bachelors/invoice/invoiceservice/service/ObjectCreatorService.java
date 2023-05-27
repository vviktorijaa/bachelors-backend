package bachelors.invoice.invoiceservice.service;

import bachelors.invoice.invoiceservice.model.Customer;
import bachelors.invoice.invoiceservice.model.Invoice;
import bachelors.invoice.invoiceservice.model.InvoiceLineItems;
import bachelors.invoice.invoiceservice.model.Vendor;

public interface ObjectCreatorService {

    void saveObjects(Customer customer, Vendor vendor, Invoice invoice, InvoiceLineItems invoiceLineItems);

}
