package challenge.man.monitortruck;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * The type Monitor truck application.
 */
@EnableMongoRepositories
@SpringBootApplication
public class MonitorTruckApplication {

	/**
	 * The entry point of spring boot application.
	 *
	 * @param args the input arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(MonitorTruckApplication.class, args);
	}

}
