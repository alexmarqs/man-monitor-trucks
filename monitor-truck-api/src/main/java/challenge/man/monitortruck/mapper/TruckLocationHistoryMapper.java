package challenge.man.monitortruck.mapper;

import challenge.man.monitortruck.dto.GpsCoordinates;
import challenge.man.monitortruck.dto.TruckLocationHistoryDto;
import challenge.man.monitortruck.model.TruckLocationHistory;

import static challenge.man.monitortruck.util.Constants.*;

/**
 * The type Truck location history mapper.
 */
public final class TruckLocationHistoryMapper extends BaseMapper<TruckLocationHistory, TruckLocationHistoryDto> {

    @Override
    public TruckLocationHistoryDto toDto(TruckLocationHistory entity) {
        return new TruckLocationHistoryDto()
                .setId(entity.getId())
                .setLocation(new GpsCoordinates(entity.getLat(), entity.getLng()))
                .setTimestamp(entity.getTimestamp());
    }

    @Override
    public TruckLocationHistory toEntity(TruckLocationHistoryDto dto) {
        throw new UnsupportedOperationException(OPERATION_NOT_USED);
    }
}
