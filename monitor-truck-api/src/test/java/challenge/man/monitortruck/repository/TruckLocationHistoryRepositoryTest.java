package challenge.man.monitortruck.repository;

import challenge.man.monitortruck.model.TruckLocationHistory;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.domain.PageRequest;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * The type Truck location history repository test.
 */
@DataMongoTest
public class TruckLocationHistoryRepositoryTest {

    @Autowired
    private TruckLocationHistoryRepository truckLocationHistoryRepository;

    @BeforeEach
    public void setUp() {
        truckLocationHistoryRepository.deleteAll();

        List<TruckLocationHistory> truckLocationHistoryList = new ArrayList<>();

        TruckLocationHistory truckLocationHistory1 = new TruckLocationHistory()
                .setLicensePlate("LI-1")
                .setLat(10)
                .setLng(-10)
                .setTimestamp(Date.from(Instant.now()));
        truckLocationHistoryList.add(truckLocationHistory1);

        TruckLocationHistory truckLocationHistory2 = new TruckLocationHistory()
                .setLicensePlate("LI-1")
                .setLat(12)
                .setLng(-14)
                .setTimestamp(Date.from(Instant.now().plusSeconds(10000)));

        truckLocationHistoryList.add(truckLocationHistory2);

        truckLocationHistoryRepository.saveAll(truckLocationHistoryList);
    }

    @Test
    public void findAllLatestPositionsOrderByTimestamp() {
        List<TruckLocationHistory> truckLocationHistories =
                truckLocationHistoryRepository.findByLicensePlateOrderByTimestampDesc("LI-1", PageRequest.of(0, 10));

        assertThat(truckLocationHistories.size(), is(2));
        assertThat(truckLocationHistories.get(0).getLat(), Matchers.equalTo(12.0));
    }

    @Test
    public void findOnlyOnePositionOrderByTimestamp() {
        List<TruckLocationHistory> truckLocationHistories =
                truckLocationHistoryRepository.findByLicensePlateOrderByTimestampDesc("LI-1", PageRequest.of(0, 1));

        assertThat(truckLocationHistories.size(), is(1));
        assertThat(truckLocationHistories.get(0).getLat(), Matchers.equalTo(12.0));
    }

}