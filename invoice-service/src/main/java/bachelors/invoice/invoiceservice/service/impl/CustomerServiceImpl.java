package bachelors.invoice.invoiceservice.service.impl;

import bachelors.invoice.invoiceservice.model.Customer;
import bachelors.invoice.invoiceservice.repository.CustomerRepository;
import bachelors.invoice.invoiceservice.service.CustomerService;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return this.customerRepository.save(customer);
    }
}
