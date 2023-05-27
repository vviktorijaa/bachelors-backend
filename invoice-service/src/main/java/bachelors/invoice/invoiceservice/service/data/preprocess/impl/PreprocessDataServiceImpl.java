package bachelors.invoice.invoiceservice.service.data.preprocess.impl;

import bachelors.invoice.invoiceservice.mapper.FieldToObjectMapper;
import bachelors.invoice.invoiceservice.model.*;
import bachelors.invoice.invoiceservice.service.ObjectCreatorService;
import bachelors.invoice.invoiceservice.service.data.preprocess.PreprocessDataService;
import bachelors.invoice.invoiceservice.service.data.preprocess.StructureDataService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PreprocessDataServiceImpl implements PreprocessDataService {

    private final StructureDataService structureDataService;
    private final ObjectCreatorService objectCreatorService;

    public PreprocessDataServiceImpl(StructureDataService structureDataService,
                                     ObjectCreatorService objectCreatorService) {
        this.structureDataService = structureDataService;
        this.objectCreatorService = objectCreatorService;
    }

    @Override
    public List<List<List<Double>>> preprocessData(final JsonNode jsonNode) {
        HashMap<String, List<ConfidenceRegionValue>> result = structureDataService.createHashMap(jsonNode);
        Customer customer = new Customer();
        Vendor vendor = new Vendor();
        Invoice invoice = new Invoice();
        InvoiceLineItems invoiceLineItems = new InvoiceLineItems();
        for (Map.Entry<String, List<ConfidenceRegionValue>> entry : result.entrySet()) {
            FieldToObjectMapper.createObjects(entry.getKey(), entry.getValue(), customer, vendor, invoice, invoiceLineItems);
        }
        objectCreatorService.saveObjects(customer, vendor, invoice, invoiceLineItems);
        return getRegions(result);
    }

    private List<List<List<Double>>> getRegions(HashMap<String, List<ConfidenceRegionValue>> result) {
        List<List<List<Double>>> regions = new ArrayList<>();
        for (List<ConfidenceRegionValue> values : result.values()) {
            for (ConfidenceRegionValue confidenceRegionValue : values) {
                List<List<Double>> coordinates = confidenceRegionValue.getCoordinates();
                if (coordinates != null) {
                    regions.add(coordinates);
                }
            }
        }
        return regions;
    }
}
