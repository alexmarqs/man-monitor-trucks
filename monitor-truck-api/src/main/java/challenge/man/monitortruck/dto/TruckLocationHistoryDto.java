package challenge.man.monitortruck.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * The type Truck location history dto.
 */
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString
public class TruckLocationHistoryDto {

    /**
     * The Id.
     */
    private String id;

    /**
     * The Location.
     */
    private GpsCoordinates location;

    /**
     * The Timestamp.
     */
    private Date timestamp;

}
