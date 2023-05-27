package bachelors.invoice.invoiceservice.service.impl;

import bachelors.invoice.invoiceservice.model.InvoiceLineItems;
import bachelors.invoice.invoiceservice.repository.InvoiceLineItemsRepository;
import bachelors.invoice.invoiceservice.service.InvoiceLineItemsService;
import org.springframework.stereotype.Service;

@Service
public class InvoiceLineItemsServiceImpl implements InvoiceLineItemsService {

    private final InvoiceLineItemsRepository invoiceLineItemsRepository;

    public InvoiceLineItemsServiceImpl(InvoiceLineItemsRepository invoiceLineItemsRepository) {
        this.invoiceLineItemsRepository = invoiceLineItemsRepository;
    }

    @Override
    public InvoiceLineItems saveInvoiceLineItems(InvoiceLineItems invoiceLineItems) {
        return this.invoiceLineItemsRepository.save(invoiceLineItems);
    }
}
