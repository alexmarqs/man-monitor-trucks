package challenge.man.monitortruck.service;

import challenge.man.monitortruck.dto.TruckLocationHistoryDto;

import java.util.List;

/**
 * The interface Monitor truck service.
 */
public interface MonitorTruckService {

    /**
     * Gets latest positions by license plate.
     *
     * @param licensePlate the license plate
     * @param maxPos the max positions
     * @return the latest positions by license plate
     */
    List<TruckLocationHistoryDto> getLatestPositionsByLicensePlate(String licensePlate, int maxPos);

}
