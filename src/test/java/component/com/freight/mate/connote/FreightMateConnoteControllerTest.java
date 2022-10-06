package component.com.freight.mate.connote;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.freight.mate.connote.MsFreightMateConnoteApplication;
import com.freight.mate.connote.api.ConnoteNumberRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({SpringExtension.class})
@SpringBootTest(classes = MsFreightMateConnoteApplication.class)
@AutoConfigureMockMvc
public class FreightMateConnoteControllerTest {

    @Autowired
    MockMvc mockMvc;
    String generateConsignmentUrl = "/v1/consignment/generate";
    String connoteNumberRequest;

    @BeforeEach
    public void setUp() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        connoteNumberRequest = mapper.writeValueAsString(ConnoteNumberRequest.builder()
                .accountNumber("123ABC")
                .carrierName("FreightmateCourierCo")
                .digits(10)
                .lastUsedIndex(19604L)
                .rangeStart(19000L)
                .rangeEnd(20000L)
                .build());

    }


    @Test
    public void testGenerateConsignmentNumberReturnSuccess() throws Exception{
        mockMvc.perform(post(generateConsignmentUrl)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(connoteNumberRequest))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    public void testGenerateConsignmentNumberWithLastIndexOutOfRangeReturnBadRequest() throws Exception{
        connoteNumberRequest = connoteNumberRequest.replace("19604", "196040");
        System.out.println(connoteNumberRequest);
        mockMvc.perform(post(generateConsignmentUrl)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(connoteNumberRequest))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"));
    }
}
