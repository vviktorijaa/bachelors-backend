package bachelors.invoice.invoiceservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "invoice")
public class Invoice {

    @Id
    private String invoiceNumber;

    @Column(name = "date_issued")
    private LocalDate dateIssued;

    @Column(name = "place_issued")
    private String placeIssued;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "currency")
    private String currency;

    @Column(name = "billing_period_from")
    private LocalDate billingPeriodFrom;

    @Column(name = "billing_period_to")
    private LocalDate billingPeriodTo;

    @Column(name = "amount")
    private BigDecimal totalAmount;

    @Column(name = "invoice_debt")
    private BigDecimal invoiceDebt;

    @Column(name = "debt_date")
    private LocalDate debtDate;

    @Column(name = "amount_overpaid")
    private BigDecimal amountOverpaid;

    @Column(name = "date_overpaid")
    private LocalDate dateOverpaid;

    @Column(name = "discount")
    private BigDecimal discountTotalAmount;

    @Column(name = "vat_rate")
    private int vatRate;

    @Column(name = "is_overpaid")
    private String isOverpaid;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customerId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private Vendor vendorId;

    @JsonIgnore
    @OneToOne(mappedBy = "invoiceNumber")
    private InvoiceScan invoiceScan;

    @JsonIgnore
    @OneToMany(mappedBy = "invoiceNumber")
    private List<InvoiceLineItems> invoiceLineItems;
}
