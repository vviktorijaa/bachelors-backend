package bachelors.invoice.invoiceservice.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Collection;

@Component
public final class PreprocessDataUtil {

    private static final Logger LOG = LoggerFactory.getLogger(PreprocessDataUtil.class);

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MM/yyyy");
    private static final DateTimeFormatter FORMATTER2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter FORMATTER3 = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final DateTimeFormatter FORMATTER4 = DateTimeFormatter.ofPattern("dd.MM.yy");

    private PreprocessDataUtil() {
    }

    public static BigDecimal convertToBigDecimal(final String key, final String amount) {
        if (key.equals("total_amount") && (amount == null || amount.isEmpty())) {
            return BigDecimal.ZERO;
        }
        if (amount == null || amount.isEmpty()) {
            return null;
        }
        try {
            return parseBigDecimal(amount);
        } catch (NumberFormatException ex) {
            return BigDecimal.ZERO;
        }
    }

    private static BigDecimal parseBigDecimal(String amount) {
        return new BigDecimal(amount.replace(",", ".")).setScale(3);
    }

    public static Integer convertToInt(final String s) {
        if (s == null) {
            return 0;
        }
        try {
            return Integer.parseInt(extractNumericCharacters(s));
        } catch (NumberFormatException ex) {
            LOG.info("Cannot parse string {} to Integer. String contains non-numeric characters.", s, ex);
            return 0;
        }
    }

    private static String extractNumericCharacters(String s) {
        return s.replaceAll("[^0-9]", "");
    }

    public static LocalDate convertToDate(String key, String date) {
        if (date == null) {
            if (isKeyMandatory(key)) {
                return LocalDate.now();
            }
            return null;
        }
        try {
            DateTimeFormatter dateTimeFormatter = createDateTimeFormatter();
            return LocalDate.parse(date, dateTimeFormatter);
        } catch (RuntimeException ex) {
            if (isKeyMandatory(key)) {
                return LocalDate.now();
            }
            LOG.info("Cannot parse string {} to LocalDate.", date, ex);
            return null;
        }
    }

    private static boolean isKeyMandatory(String key) {
        return key.equals("billing_period_from") || key.equals("date_issued") || key.equals("due_date");
    }

    public static String concat(final Collection<String> stringList) {
        return String.join(",", stringList);
    }

    public static boolean checkIfIsNumber(final String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (final NumberFormatException ex) {
            return false;
        }
    }

    public static String onlyString(final String s) {
        if (checkIfIsNumber(s)) {
            return "";
        }
        return s;
    }

    public static int onlyNumber(final String s) {
        if (checkIfIsNumber(s)) {
            return Integer.parseInt(s);
        }
        return 0;
    }

    private static DateTimeFormatter createDateTimeFormatter() {
        return new DateTimeFormatterBuilder()
                .appendOptional(FORMATTER)
                .appendOptional(FORMATTER2)
                .appendOptional(FORMATTER3)
                .appendOptional(FORMATTER4)
                .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
                .toFormatter();
    }
}
