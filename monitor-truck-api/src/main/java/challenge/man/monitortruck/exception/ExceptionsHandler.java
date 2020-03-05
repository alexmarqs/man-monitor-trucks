package challenge.man.monitortruck.exception;

import challenge.man.monitortruck.dto.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * The type Exceptions handler.
 */
@ControllerAdvice
public class ExceptionsHandler {

    /**
     * Handle monitor truck exception response entity.
     *
     * @param ex the ex
     * @return the response entity
     */
    @ExceptionHandler(value = MonitorTruckException.class)
    public ResponseEntity<ErrorResponse> handleMonitorTruckException(MonitorTruckException ex) {
        ErrorResponse errorResponse = new ErrorResponse()
                .setMessage(ex.getMessage())
                .setDetails(Collections.emptyList())
                .setTimestamp(new Date());
        return ResponseEntity.status(ex.getErrorType().getErrorStatus()).body(errorResponse);
    }

    /**
     * Handle constraint violation exception response entity.
     *
     * @param ex the ex
     * @return the response entity
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException ex) {
        ErrorResponse errorResponse = new ErrorResponse()
                .setMessage(ErrorType.VALIDATION_FAILED.getMessageErrorTemplate())
                .setDetails(StreamSupport.stream(ex.getConstraintViolations().spliterator(), false)
                        .map(viol -> viol.getMessage())
                        .collect(Collectors.toList()))
                .setTimestamp(new Date());
        return ResponseEntity.status(ErrorType.VALIDATION_FAILED.getErrorStatus()).body(errorResponse);
    }

}
