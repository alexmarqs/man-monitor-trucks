package challenge.man.monitortruck.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The type Gps coordinates.
 */
@Getter
@Setter
@AllArgsConstructor
@ToString
public class GpsCoordinates {

    /**
     * The Lat.
     */
    private double lat;

    /**
     * The Lng.
     */
    private double lng;

}
