package bachelors.invoice.invoiceservice.mapper;

import bachelors.invoice.invoiceservice.model.*;
import bachelors.invoice.invoiceservice.utils.PreprocessDataUtil;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public record FieldToObjectMapper() {

    public static void createObjects(final String key,
                              final List<ConfidenceRegionValue> value,
                              final Customer customer,
                              final Vendor vendor,
                              final Invoice invoice,
                              final InvoiceLineItems invoiceLineItems) {
        if (!value.isEmpty()) {
            String v = "";
            List<String> stringList = new ArrayList<>();
            if (key.equals("invoice_number") || key.equals("customer_name") || key.equals("customer_address")) {
                for (ConfidenceRegionValue values : value) {
                    stringList.add(values.getValue());
                }
            } else {
                v = value.get(0).getValue();
            }
            switch (key) {
                case "vendor_name" -> vendor.setVendorName(v);
                case "vendor_address" -> vendor.setVendorAddress(v);
                case "vendor_email" -> vendor.setVendorEmail(v);
                case "vendor_website" -> vendor.setVendorWebsite(v);
                case "customer_number" -> customer.setCustomerNumber(v);
                case "customer_name" -> customer.setCustomerName(v);
                case "customer_address" -> customer.setCustomerAddress(v);
                case "invoice_number" -> invoice.setInvoiceNumber(PreprocessDataUtil.concat(stringList));
                case "date_overpaid" -> invoice.setDateOverpaid(PreprocessDataUtil.convertToDate(key, v));
                case "date_issued" -> invoice.setDateIssued(PreprocessDataUtil.convertToDate(key, v));
                case "place_issued" -> invoice.setPlaceIssued(v);
                case "due_date" -> invoice.setDueDate(PreprocessDataUtil.convertToDate(key, v));
                case "currency" -> invoice.setCurrency(v);
                case "billing_period_from" -> invoice.setBillingPeriodFrom(PreprocessDataUtil.convertToDate(key, v));
                case "billing_period_to" -> invoice.setBillingPeriodTo(PreprocessDataUtil.convertToDate(key, v));
                case "total_amount" -> invoice.setTotalAmount(PreprocessDataUtil.convertToBigDecimal(key, v));
                case "invoice_debt" -> invoice.setInvoiceDebt(PreprocessDataUtil.convertToBigDecimal(key, v));
                case "debt_date" -> invoice.setDebtDate(PreprocessDataUtil.convertToDate(key, v));
                case "amount_overpaid" -> invoice.setAmountOverpaid(PreprocessDataUtil.convertToBigDecimal(key, v));
                case "is_overpaid" -> invoice.setIsOverpaid(v);
                case "discount_total_amount" -> invoice.setDiscountTotalAmount(PreprocessDataUtil.convertToBigDecimal(key, v));
                case "vat_value_total_amount" -> invoice.setVatRate(PreprocessDataUtil.convertToInt(v));
                case "services_quantity" -> invoiceLineItems.setQuantity(PreprocessDataUtil.convertToInt(v));
                case "services_vat_value" -> invoiceLineItems.setVatPerService(PreprocessDataUtil.convertToInt(v));
                case "services_discount" -> invoiceLineItems.setDiscount(PreprocessDataUtil.convertToBigDecimal(key, v));
                case "services_amount" -> invoiceLineItems.setUnitPrice(PreprocessDataUtil.convertToBigDecimal(key, v));
                default -> throw new RuntimeException("The field " + key + " is not implemented yet.");
            }
        }
    }
}
