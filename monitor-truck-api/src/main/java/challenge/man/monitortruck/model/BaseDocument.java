package challenge.man.monitortruck.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

/**
 * The Base document model.
 *
 * @param <I> the type parameter
 */
@Getter
@Setter
@ToString
public abstract class BaseDocument<I> {

    /**
     * The Id.
     */
    @Id
    private I id;

}
