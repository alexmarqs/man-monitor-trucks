package challenge.man.monitortruck.repository;

import challenge.man.monitortruck.model.TruckLocationHistory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Truck location history repository.
 */
@Repository
public interface TruckLocationHistoryRepository extends MongoRepository<TruckLocationHistory, String> {

    /**
     * Find by license plate order by timestamp desc optional.
     *
     * @param licensePlate the license plate
     * @param page         the page
     * @return the list
     */
    List<TruckLocationHistory> findByLicensePlateOrderByTimestampDesc(String licensePlate, Pageable page);

}