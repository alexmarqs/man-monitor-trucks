package challenge.man.monitortruck.config;

import challenge.man.monitortruck.repository.TruckLocationHistoryRepository;
import challenge.man.monitortruck.service.MonitorTruckService;
import challenge.man.monitortruck.service.MonitorTruckServiceImpl;
import org.slf4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The Service configuration.
 */
@Configuration
public class ServiceConfiguration {

    /**
     * Monitor truck service.
     *
     * @param logger                         the logger
     * @param truckLocationHistoryRepository the truck location history repository
     * @return the monitor truck service
     */
    @Bean
    public MonitorTruckService monitorTruckService(Logger logger, TruckLocationHistoryRepository truckLocationHistoryRepository) {
        return new MonitorTruckServiceImpl(logger, truckLocationHistoryRepository);
    }

}
