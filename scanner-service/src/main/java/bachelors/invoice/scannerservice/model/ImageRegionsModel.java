package bachelors.invoice.scannerservice.model;

import java.util.List;

public record ImageRegionsModel (String image, List<List<List<Double>>> regions) {
}