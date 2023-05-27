package bachelors.invoice.invoiceservice;

import bachelors.invoice.invoiceservice.utils.PreprocessDataUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class InvoiceServiceApplicationTests {

    @Test
    void convertStringToBigDecimalTest() {
        assertEquals(BigDecimal.valueOf(729.000).setScale(3), PreprocessDataUtil.convertToBigDecimal("total_amount", "729.000"));
        assertEquals(BigDecimal.valueOf(15.000).setScale(3), PreprocessDataUtil.convertToBigDecimal("invoice_debt", "15"));
        assertEquals(BigDecimal.valueOf(300.150).setScale(3), PreprocessDataUtil.convertToBigDecimal("services_discount", "300,15"));
        assertEquals(BigDecimal.valueOf(55.500).setScale(3), PreprocessDataUtil.convertToBigDecimal("services_amount", "55.5"));
        assertEquals(BigDecimal.valueOf(105.555).setScale(3), PreprocessDataUtil.convertToBigDecimal("total_amount", "105.555"));
        assertEquals(BigDecimal.valueOf(10.000).setScale(3), PreprocessDataUtil.convertToBigDecimal("invoice_debt", "10.000"));
        assertEquals(BigDecimal.ZERO, PreprocessDataUtil.convertToBigDecimal("total_amount", ""));
        assertEquals(BigDecimal.ZERO, PreprocessDataUtil.convertToBigDecimal("total_amount", null));
        assertNull(PreprocessDataUtil.convertToBigDecimal("services_amount", null));
        assertNull(PreprocessDataUtil.convertToBigDecimal("services_amount", ""));
        assertEquals(BigDecimal.ZERO, PreprocessDataUtil.convertToBigDecimal("services_amount", "abcd"));
    }

    @Test
    void testConvertToInt() {
        assertEquals(18, PreprocessDataUtil.convertToInt("18%"));
        assertEquals(0, PreprocessDataUtil.convertToInt(null));
        assertEquals(0, PreprocessDataUtil.convertToInt(""));
        assertEquals(18, PreprocessDataUtil.convertToInt("./ 18%"));
    }

    @Test
    void testConvertToDate() {
        assertEquals(LocalDate.of(2023, 5, 2), PreprocessDataUtil.convertToDate("billing_period_from", "02.05.2023"));
        assertEquals(LocalDate.of(2023, 2, 1), PreprocessDataUtil.convertToDate("billing_period_from", "02/2023"));
        assertEquals(LocalDate.of(2023, 3, 20), PreprocessDataUtil.convertToDate("billing_period_from", "2023-03-20"));
        assertEquals(LocalDate.of(2023, 1, 2), PreprocessDataUtil.convertToDate("billing_period_from", "02.01.2023"));
        assertEquals(LocalDate.of(2023, 1, 1), PreprocessDataUtil.convertToDate("billing_period_to", "01/2023"));
        assertEquals(LocalDate.now(), PreprocessDataUtil.convertToDate("billing_period_from", ""));
        assertNull(PreprocessDataUtil.convertToDate("date_overpaid", ""));
        assertEquals(LocalDate.now(), PreprocessDataUtil.convertToDate("date_issued", ""));
        assertEquals(LocalDate.now(), PreprocessDataUtil.convertToDate("due_date", ""));
        assertNull(PreprocessDataUtil.convertToDate("debt_date", ""));
        assertNull(PreprocessDataUtil.convertToDate("billing_date_to", ""));
    }

    @Test
    void testConcat() {
        //The HashMap class does not guarantee any specific order of its entries.
        //If you need to process the entries of a HashMap in a specific order, you can use the LinkedHashMap class instead
        LinkedHashMap<String, HashMap<Double, String>> hashMap = new LinkedHashMap<>();
        LinkedHashMap<Double, String> map = new LinkedHashMap<>();
        map.put(0.99, "Addr");
        map.put(0.98, "Ul.");
        map.put(0.95, "12");
        map.put(0.9, "Skopje");
        hashMap.put("customer_address", map);
        String concatenated = PreprocessDataUtil.concat(hashMap.entrySet().stream().findFirst().get().getValue().values());
        assertEquals("Addr,Ul.,12,Skopje", concatenated);
    }

    @Test
    void testConvertToLocalDate() {
        assertEquals(LocalDate.of(2023, 2, 1), PreprocessDataUtil.convertToDate("billing_date_from", "02/2023"));
        assertEquals(LocalDate.of(2023, 3, 20), PreprocessDataUtil.convertToDate("billing_date_from", "2023-03-20"));
        assertEquals(LocalDate.of(2023, 1, 2), PreprocessDataUtil.convertToDate("billing_date_from", "02.01.2023"));
    }
}
