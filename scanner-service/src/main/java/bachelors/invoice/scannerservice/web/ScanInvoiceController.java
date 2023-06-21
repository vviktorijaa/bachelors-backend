package bachelors.invoice.scannerservice.web;

import bachelors.invoice.invoiceservice.service.data.preprocess.PreprocessDataService;
import bachelors.invoice.scannerservice.model.ImageRegionsModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.mindee.DocumentToParse;
import com.mindee.MindeeClient;
import com.mindee.MindeeClientInit;
import com.mindee.parsing.CustomEndpoint;
import com.mindee.parsing.common.Document;
import com.mindee.parsing.custom.CustomV1Inference;
import com.mindee.parsing.custom.CustomV1PagePrediction;
import com.mindee.utils.MindeeException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ScanInvoiceController {

    private static final Logger LOG = LoggerFactory.getLogger(ScanInvoiceController.class);

    @Value("${API_KEY}")
    private String apiKey;

    @Value("${ENDPOINT}")
    private String endpoint;

    @Value("${ACCOUNT}")
    private String account;

    private final PreprocessDataService preprocessDataService;

    public ScanInvoiceController(PreprocessDataService preprocessDataService) {
        this.preprocessDataService = preprocessDataService;
    }

    @PostMapping("/scan/invoice")
    public ResponseEntity<ImageRegionsModel> scanInvoice(@RequestPart("invoice") final MultipartFile scannedInvoice) throws IOException {
        if (scannedInvoice.isEmpty()) {
            return ResponseEntity.badRequest().body(new ImageRegionsModel("", new ArrayList<>()));
        }
        LOG.info("Sending scan to invoice-api...");

        byte[] imgBytes = scannedInvoice.getBytes();
        Document<CustomV1Inference> customDocument = getMindeeApiResponse(scannedInvoice.getBytes());
        JsonNode jsonNode = generateJsonNode(customDocument);

        LOG.info("Preprocessing response from invoice-api...");
        List<List<List<Double>>> regions = preprocessDataService.preprocessData(jsonNode);
        LOG.info("Invoice saved successfully.");

        ImageRegionsModel imageRegionsModel = generateImageRegionsModel(imgBytes, regions);
        return ResponseEntity.ok(imageRegionsModel);
    }

    public ImageRegionsModel generateImageRegionsModel(byte[] imgBytes, List<List<List<Double>>> regions) {
        return createImageRegionsModel(imgBytes, regions);
    }

    public JsonNode generateJsonNode(Document<CustomV1Inference> customDocument) throws JsonProcessingException {
        return getJsonNode(customDocument);
    }

    private ImageRegionsModel createImageRegionsModel(byte[] imgBytes, List<List<List<Double>>> regions) {
        String base64Image = Base64.getEncoder().encodeToString(imgBytes);
        ImageRegionsModel imageRegionsModel = new ImageRegionsModel(base64Image, regions);
        return imageRegionsModel;
    }

    private JsonNode getJsonNode(Document<CustomV1Inference> customDocument) throws JsonProcessingException {
        CustomV1PagePrediction jsonResponse = customDocument.getInference().getPages().get(0).getPrediction();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(objectMapper.writeValueAsString(jsonResponse));
        return jsonNode;
    }

    private Document<CustomV1Inference> getMindeeApiResponse(final byte[] binaryFile) throws IOException {
        MindeeClient mindeeClient = MindeeClientInit.create(apiKey);
        CustomEndpoint customEndpoint = new CustomEndpoint(endpoint, account);
        DocumentToParse documentToParse = mindeeClient.loadDocument(binaryFile, "filename");
        try {
            return mindeeClient.parse(documentToParse, customEndpoint);
        } catch (MindeeException ex) {
            throw new RuntimeException("Problem with parsing the file", ex);
        }
    }
}
