package challenge.man.monitortruck.controller.v1;

import challenge.man.monitortruck.dto.GpsCoordinates;
import challenge.man.monitortruck.dto.TruckLocationHistoryDto;
import challenge.man.monitortruck.exception.ErrorType;
import challenge.man.monitortruck.repository.TruckLocationHistoryRepository;
import challenge.man.monitortruck.service.MonitorTruckService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * The type Monitor truck controller test.
 */
@WebMvcTest(MonitorTruckController.class)
public class MonitorTruckControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MonitorTruckService monitorTruckService;

    @MockBean
    private Logger logger;

    @MockBean
    private TruckLocationHistoryRepository truckLocationHistoryRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String API_ENDPOINT = "/v1/monitor-truck";

    @Test
    public void whenRequestParamAndPathVariableAreInvalid_returnsError_404() throws Exception {
        mockMvc.perform(get(API_ENDPOINT + "/LI-111111111/latestPositions?maxPos=111111"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(jsonPath("$.message").value(ErrorType.VALIDATION_FAILED.getMessageErrorTemplate()));
    }

    @Test
    public void whenRequestIsValid_returnsLatestPositions_OK() throws Exception {
        TruckLocationHistoryDto truckLocationHistoryDto = new TruckLocationHistoryDto()
                .setId("a1234bcd")
                .setLocation(new GpsCoordinates(30,-10))
                .setTimestamp(new Date());

        List<TruckLocationHistoryDto> truckLocationHistoryDtoList = Arrays.asList(truckLocationHistoryDto);

        when(monitorTruckService.getLatestPositionsByLicensePlate(anyString(), anyInt()))
                .thenReturn(truckLocationHistoryDtoList);

        MvcResult mvcResult = mockMvc.perform(get(API_ENDPOINT + "/LI-1/latestPositions"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertThat(objectMapper.writeValueAsString(truckLocationHistoryDtoList), is(equalTo(mvcResult.getResponse().getContentAsString())));
    }
}