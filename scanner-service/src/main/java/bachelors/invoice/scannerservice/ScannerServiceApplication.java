package bachelors.invoice.scannerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.SpringVersion;

@SpringBootApplication
@ComponentScan("bachelors.invoice.scannerservice.web")
@ComponentScan("bachelors.invoice.invoiceservice")
public class ScannerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScannerServiceApplication.class, args);
    }

}
