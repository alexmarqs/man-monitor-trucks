package challenge.man.monitortruck.controller.v1;

import challenge.man.monitortruck.dto.ErrorResponse;
import challenge.man.monitortruck.dto.TruckLocationHistoryDto;
import challenge.man.monitortruck.service.MonitorTruckService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.hibernate.validator.constraints.Range;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Size;
import java.util.List;

import static challenge.man.monitortruck.util.Constants.*;

/**
 * The type Monitor truck rest controller.
 */
@RestController
@Validated
@RequestMapping("/v1/monitor-truck")
@Api(value = "MAN Monitor Truck API")
public class MonitorTruckController {

    /**
     * The Logger.
     */
    private final Logger logger;

    /**
     * The Monitor truck service.
     */
    private final MonitorTruckService monitorTruckService;

    /**
     * Instantiates a new Monitor truck controller.
     *
     * @param logger              the logger
     * @param monitorTruckService the monitor truck service
     */
    public MonitorTruckController(Logger logger, MonitorTruckService monitorTruckService) {
        this.logger = logger;
        this.monitorTruckService = monitorTruckService;
    }

    /**
     * Gets latest positions by license plate.
     *
     * @param licensePlate the license plate
     * @param maxPos the max positions
     * @return the latest positions by license plate
     */
    @ApiOperation(value = "Get the latest positions for a given truck by license plate ordered by timestamp. " +
            "An optional request parameter (maxPos) can be used to limit the results.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved the latest positions"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found", response = ErrorResponse.class),
        @ApiResponse(code = 400, message = "Bad url request", response = ErrorResponse.class)
    })
    @GetMapping("/{licensePlate}/latestPositions")
    public ResponseEntity<List<TruckLocationHistoryDto>> getLatestPositionsByLicensePlate(@PathVariable @Size(max = 10, message = LICENSE_INVALID_SIZE) String licensePlate,
                                                                                          @RequestParam(required = false, defaultValue = DEFAULT_MAX_POSITIONS)  @Range(min = 1, max = 100, message = MAX_POS_LIMIT) int maxPos) {
        logger.info("Entering in endpoint to get all the latest positions for the given license plate truck");
        List<TruckLocationHistoryDto> latestPositions = monitorTruckService.getLatestPositionsByLicensePlate(licensePlate.toUpperCase(), maxPos);
        return ResponseEntity.ok(latestPositions);
    }

}
