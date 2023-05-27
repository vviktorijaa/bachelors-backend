package bachelors.invoice.invoiceservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "invoice_line_items")
public class InvoiceLineItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    @Column(name = "vat_rate")
    private int vatPerService;

    @Column(name = "discount")
    private BigDecimal discount;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "invoice_number")
    private Invoice invoiceNumber;
}
