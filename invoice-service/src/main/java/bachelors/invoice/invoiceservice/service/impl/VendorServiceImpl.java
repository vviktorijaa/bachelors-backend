package bachelors.invoice.invoiceservice.service.impl;

import bachelors.invoice.invoiceservice.model.Vendor;
import bachelors.invoice.invoiceservice.repository.VendorRepository;
import bachelors.invoice.invoiceservice.service.VendorService;
import org.springframework.stereotype.Service;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;

    public VendorServiceImpl(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @Override
    public Vendor saveVendor(Vendor vendor) {
        return this.vendorRepository.save(vendor);
    }
}
