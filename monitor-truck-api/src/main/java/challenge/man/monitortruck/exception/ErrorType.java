package challenge.man.monitortruck.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

/**
 * The enum Error type.
 */
@Getter
@AllArgsConstructor
@ToString
public enum ErrorType {

    /**
     * The Enums.
     */
    RESOURCE_NOT_FOUND("%s not found", HttpStatus.NOT_FOUND),
    VALIDATION_FAILED ("Validation failed", HttpStatus.BAD_REQUEST);

    /**
     * The Message error template.
     */
    private String messageErrorTemplate;

    /**
     * The Error status.
     */
    private HttpStatus errorStatus;

}
