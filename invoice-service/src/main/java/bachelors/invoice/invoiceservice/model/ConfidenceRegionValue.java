package bachelors.invoice.invoiceservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ConfidenceRegionValue {

    private Double confidence;
    private List<List<Double>> coordinates;
    private String value;

    public ConfidenceRegionValue(Double confidence, List<List<Double>> coordinates, String value) {
        this.confidence = confidence;
        this.coordinates = coordinates;
        this.value = value;
    }
}