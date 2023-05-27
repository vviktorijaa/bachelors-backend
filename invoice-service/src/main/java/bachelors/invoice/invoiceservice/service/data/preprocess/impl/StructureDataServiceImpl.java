package bachelors.invoice.invoiceservice.service.data.preprocess.impl;

import bachelors.invoice.invoiceservice.model.ConfidenceRegionValue;
import bachelors.invoice.invoiceservice.service.data.preprocess.StructureDataService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StructureDataServiceImpl implements StructureDataService {

    private static final List<String> list = new ArrayList<>();

    public StructureDataServiceImpl() {
        list.add("customer_number");
        list.add("customer_name");
        list.add("customer_address");
        list.add("vendor_name");
        list.add("vendor_address");
        list.add("invoice_number");
        list.add("services_quantity");
        list.add("services_vat_value");
        list.add("services_amount");
        list.add("services_discount");
    }

    public HashMap<String,  List<ConfidenceRegionValue>> createHashMap(JsonNode jsonNode) {
        HashMap<String,  List<ConfidenceRegionValue>> fields = restructureData(jsonNode);
        return reduceHashMap(fields, 0.0);
    }

    public HashMap<String, List<ConfidenceRegionValue>> restructureData(JsonNode jsonNode) {
        HashMap<String,  List<ConfidenceRegionValue>> fields = new HashMap<>();
        Iterator<String> fieldNames = jsonNode.fieldNames();
        while (fieldNames.hasNext()) {
            String fieldName = fieldNames.next();
            JsonNode values = jsonNode.get(fieldName).get("values");
            List<ConfidenceRegionValue> confidenceRegionValues = new ArrayList<>();
            for (JsonNode valueNode : values) {
                Double confidence = valueNode.get("confidence").asDouble();
                String value = valueNode.get("content").asText();
                List<List<Double>> coordinates = extractRegion(valueNode);
                ConfidenceRegionValue confidenceRegionValue = new ConfidenceRegionValue(confidence,
                        coordinates, value);
                confidenceRegionValues.add(confidenceRegionValue);
            }
            fields.put(fieldName, confidenceRegionValues);
        }
        return fields;
    }

    private List<List<Double>> extractRegion(JsonNode valueNode) {
        List<List<Double>> coordinatesList = new ArrayList<>();
        JsonNode region = valueNode.get("polygon");
        for (JsonNode coordinates : region) {
            for (JsonNode coordinate : coordinates) {
                List<Double> point = new ArrayList<>();
                for (JsonNode c : coordinate) {
                    point.add(c.asDouble());
                }
                coordinatesList.add(point);
            }
        }
        return coordinatesList;
    }

    public HashMap<String, List<ConfidenceRegionValue>> reduceHashMap(
            HashMap<String, List<ConfidenceRegionValue>> fields, Double maxConfidence) {
        HashMap<String, List<ConfidenceRegionValue>> result = new HashMap<>();
        for (Map.Entry<String,  List<ConfidenceRegionValue>> entry : fields.entrySet()) {
            String key = entry.getKey();
            if (list.contains(key)) {
                result.put(key, entry.getValue());
                continue;
            }
            List<ConfidenceRegionValue> valuesMap = reduceValuesMap(entry.getValue(), maxConfidence);
            result.put(key, valuesMap);
        }
        return result;
    }

    private List<ConfidenceRegionValue> reduceValuesMap(List<ConfidenceRegionValue> values, Double maxConfidence) {
        List<ConfidenceRegionValue> reducedMap = new ArrayList<>();
        ConfidenceRegionValue confidenceRegionValue = new ConfidenceRegionValue();
        for (ConfidenceRegionValue value : values) {
            Double confidence = value.getConfidence();
            if (confidence.compareTo(maxConfidence) > 0) {
                maxConfidence = confidence;
                confidenceRegionValue.setConfidence(maxConfidence);
                confidenceRegionValue.setValue(value.getValue());
                confidenceRegionValue.setCoordinates(value.getCoordinates());
            }
        }
        reducedMap.add(confidenceRegionValue);
        return reducedMap;
    }
}
