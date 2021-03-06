package challenge.man.monitortruck.service;

import challenge.man.monitortruck.dto.TruckLocationHistoryDto;
import challenge.man.monitortruck.exception.ErrorType;
import challenge.man.monitortruck.exception.MonitorTruckException;
import challenge.man.monitortruck.mapper.TruckLocationHistoryMapper;
import challenge.man.monitortruck.model.TruckLocationHistory;
import challenge.man.monitortruck.repository.TruckLocationHistoryRepository;
import org.slf4j.Logger;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * The type Monitor truck service (implementation).
 */
public class MonitorTruckServiceImpl implements MonitorTruckService {

    /**
     * The Logger.
     */
    private final Logger logger;

    /**
     * The Truck location history repository.
     */
    private final TruckLocationHistoryRepository truckLocationHistoryRepository;

    /**
     * Instantiates a new Monitor truck service.
     *
     * @param logger                         the logger
     * @param truckLocationHistoryRepository the truck location history repository
     */
    public MonitorTruckServiceImpl(Logger logger, TruckLocationHistoryRepository truckLocationHistoryRepository) {
        this.logger = logger;
        this.truckLocationHistoryRepository = truckLocationHistoryRepository;
    }

    @Override
    public List<TruckLocationHistoryDto> getLatestPositionsByLicensePlate(String licensePlate, int maxPos) {
        logger.debug("Entering in service to get latest positions by license plate = {} with max positions = {}", licensePlate, maxPos);

        List<TruckLocationHistory> truckLocationHistoryList = truckLocationHistoryRepository
                .findByLicensePlateOrderByTimestampDesc(licensePlate, PageRequest.of(0, maxPos));

        if (CollectionUtils.isEmpty(truckLocationHistoryList)) {
           logger.warn("No latest positions available in database for the license plate {}", licensePlate);
           throw new MonitorTruckException(ErrorType.RESOURCE_NOT_FOUND, "License plate " + licensePlate);
        }

        logger.debug("{} records were loaded from the database", truckLocationHistoryList.size());

        return new TruckLocationHistoryMapper().toDtoList(truckLocationHistoryList);
    }

}
