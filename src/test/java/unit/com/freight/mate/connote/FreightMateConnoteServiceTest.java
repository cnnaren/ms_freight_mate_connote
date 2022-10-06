package unit.com.freight.mate.connote;

import com.freight.mate.connote.api.ConnoteNumberRequest;
import com.freight.mate.connote.api.ConnoteNumberResponse;
import com.freight.mate.connote.api.Status;
import com.freight.mate.connote.controller.FreightMateConnoteController;
import com.freight.mate.connote.exception.MicroserviceException;
import com.freight.mate.connote.service.FreightMateConnoteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class FreightMateConnoteServiceTest {

    @InjectMocks
    private FreightMateConnoteService freightMateService;

    private HttpHeaders httpHeaders;

    @BeforeEach
    public void setUp() {
        httpHeaders = new HttpHeaders();
        httpHeaders.add("content-type", MediaType.APPLICATION_JSON_VALUE);
    }

    @Test
    public void testGenerateConsignmentNumberThrowIndexRangeException() {
        ConnoteNumberRequest connoteNumberRequest = ConnoteNumberRequest.builder()
                .accountNumber("123ABC")
                .carrierName("FreightmateCourierCo")
                .digits(10)
                .lastUsedIndex(18604L)
                .rangeStart(19000L)
                .rangeEnd(20000L)
                .build();
        MicroserviceException exception = assertThrows(MicroserviceException.class, () -> freightMateService.generateConsignmentNumber(connoteNumberRequest, httpHeaders));
        assertEquals(exception.getStatus(), Status.BAD_REQUEST);
        assertEquals(exception.getMessage(), "The index provided is out of range");
    }
}
