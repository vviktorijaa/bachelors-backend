package bachelors.invoice.scannerservice;

import bachelors.invoice.invoiceservice.service.data.preprocess.PreprocessDataService;
import bachelors.invoice.scannerservice.web.ScanInvoiceController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ScannerServiceApplicationTests {

    @Mock
    private PreprocessDataService preprocessDataService;

    private ScanInvoiceController scanInvoiceController;

    @BeforeEach
    void setup() {

    }

    @Test
    void contextLoads() {

    }

}
