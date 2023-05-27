package bachelors.invoice.invoiceservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "vendor")
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vendor_name")
    private String vendorName;

    @Column(name = "vendor_address")
    private String vendorAddress;

    @Column(name = "vendor_email")
    private String vendorEmail;

    @Column(name = "vendor_website")
    private String vendorWebsite;

    @JsonIgnore
    @OneToMany(mappedBy = "vendorId")
    private List<Invoice> invoice;
}
