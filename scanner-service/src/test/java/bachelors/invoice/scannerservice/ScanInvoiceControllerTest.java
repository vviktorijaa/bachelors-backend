package bachelors.invoice.scannerservice;

import bachelors.invoice.scannerservice.model.ImageRegionsModel;
import bachelors.invoice.scannerservice.web.ScanInvoiceController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

class ScanInvoiceControllerTest {

    @Test
    void testCreateImageRegionsModel() {
        byte[] imgBytes = {1, 2, 3, 4, 5};
        List<List<List<Double>>> regions = new ArrayList<>();

        String base64Image = Base64.getEncoder().encodeToString(imgBytes);
        ImageRegionsModel expected = new ImageRegionsModel(base64Image, regions);

        ScanInvoiceController scanInvoiceControllerMock = Mockito.mock(ScanInvoiceController.class);
        Mockito.when(scanInvoiceControllerMock.generateImageRegionsModel(Mockito.any(byte[].class), Mockito.anyList()))
                .thenCallRealMethod();

        ImageRegionsModel result = scanInvoiceControllerMock.generateImageRegionsModel(imgBytes, regions);
        Assertions.assertEquals(expected, result);
    }

}
