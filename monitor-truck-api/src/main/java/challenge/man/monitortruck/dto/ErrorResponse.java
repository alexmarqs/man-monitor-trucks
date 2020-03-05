package challenge.man.monitortruck.dto;

import lombok.*;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**
 * The type Error response.
 */
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@ToString
public class ErrorResponse {

    /**
     * The Message.
     */
    private String message;

    /**
     * The Details.
     */
    private List<String> details;

    /**
     * The Timestamp.
     */
    private Date timestamp;

}
