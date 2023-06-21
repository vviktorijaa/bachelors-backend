package bachelors.invoice.invoiceservice;

import bachelors.invoice.invoiceservice.model.ConfidenceRegionValue;
import bachelors.invoice.invoiceservice.service.ObjectCreatorService;
import bachelors.invoice.invoiceservice.service.data.preprocess.PreprocessDataService;
import bachelors.invoice.invoiceservice.service.data.preprocess.StructureDataService;
import bachelors.invoice.invoiceservice.service.data.preprocess.impl.PreprocessDataServiceImpl;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PreprocessDataServiceTest {

    @Mock
    private StructureDataService structureDataService;

    @Mock
    private ObjectCreatorService objectCreatorService;

    private PreprocessDataService preprocessDataService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        preprocessDataService = new PreprocessDataServiceImpl(structureDataService, objectCreatorService);
    }

    @Test
    void testPreprocessData() {
        JsonNode jsonNode = Mockito.mock(JsonNode.class);
        HashMap<String, List<ConfidenceRegionValue>> result = new HashMap<>();
        result.put("field1", new ArrayList<>());
        result.put("field2", new ArrayList<>());

        Mockito.when(structureDataService.createHashMap(ArgumentMatchers.any(JsonNode.class)))
                .thenReturn(result);

        List<List<List<Double>>> regions = preprocessDataService.preprocessData(jsonNode);

        Mockito.verify(structureDataService).createHashMap(jsonNode);

        Assertions.assertNotNull(regions);
        Assertions.assertEquals(0, regions.size());
    }
}
