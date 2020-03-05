package challenge.man.monitortruck.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * The Truck location history document model.
 */
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@Document(collection = "truck_location_history")
public class TruckLocationHistory extends BaseDocument<String> {

    /**
     * The License plate.
     */
    @Indexed
    private String licensePlate;

    /**
     * The Lat.
     */
    private double lat;

    /**
     * The Lng.
     */
    private double lng;

    /**
     * The Timestamp.
     */
    private Date timestamp;

}
