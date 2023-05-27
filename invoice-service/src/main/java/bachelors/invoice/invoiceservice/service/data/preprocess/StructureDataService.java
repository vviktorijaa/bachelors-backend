package bachelors.invoice.invoiceservice.service.data.preprocess;

import bachelors.invoice.invoiceservice.model.ConfidenceRegionValue;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.HashMap;
import java.util.List;

public interface StructureDataService {

    HashMap<String, List<ConfidenceRegionValue>> createHashMap(JsonNode jsonNode);

    HashMap<String,  List<ConfidenceRegionValue>> restructureData(JsonNode jsonNode);

    HashMap<String,  List<ConfidenceRegionValue>> reduceHashMap(HashMap<String,
            List<ConfidenceRegionValue>> fields, Double maxConfidence);
}
