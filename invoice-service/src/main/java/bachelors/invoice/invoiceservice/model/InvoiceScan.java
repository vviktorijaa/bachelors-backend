package bachelors.invoice.invoiceservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "invoice_scan")
public class InvoiceScan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_scanned")
    private OffsetDateTime scanTimestamp;

    @JsonIgnore
    @NonNull
    @OneToOne
    @JoinColumn(name = "invoice_number")
    private Invoice invoiceNumber;

    @JsonIgnore
    @NonNull
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User userId;

    public InvoiceScan(OffsetDateTime scanTimestamp, @NonNull Invoice invoiceNumber, @NonNull User userId) {
        this.scanTimestamp = scanTimestamp;
        this.invoiceNumber = invoiceNumber;
        this.userId = userId;
    }
}

