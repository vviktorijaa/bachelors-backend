package bachelors.invoice.invoiceservice.service.data.preprocess;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public interface PreprocessDataService {

    List<List<List<Double>>> preprocessData(JsonNode jsonNode);
}
