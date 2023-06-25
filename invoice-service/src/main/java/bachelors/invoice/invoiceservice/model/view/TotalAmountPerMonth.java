package bachelors.invoice.invoiceservice.model.view;

import jakarta.persistence.*;

@Entity
@Table(name = "total_amount_per_month")
public class TotalAmountPerMonth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "month_")
    private Integer month;

    @Column(name = "number_invoices")
    private Long numberOfInvoices;

    @Column(name = "total_amount")
    private Double totalAmount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Long getNumberOfInvoices() {
        return numberOfInvoices;
    }

    public void setNumberOfInvoices(Long numberOfInvoices) {
        this.numberOfInvoices = numberOfInvoices;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }
}