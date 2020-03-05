package challenge.man.monitortruck.exception;

import lombok.Getter;

/**
 * The type Monitor truck exception.
 */
@Getter
public class MonitorTruckException extends RuntimeException {

    /**
     * The Error type.
     */
    private ErrorType errorType;

    /**
     * The Message param.
     */
    private String messageParam;

    /**
     * Instantiates a new Monitor truck exception.
     *
     * @param errorType    the error type
     * @param messageParam the message param
     */
    public MonitorTruckException(ErrorType errorType, String messageParam) {
        super(getErrorMessageFromParam(errorType, messageParam));
        this.errorType = errorType;
        this.messageParam = messageParam;
    }

    /**
     * Gets error message from param.
     *
     * @param errorType    the error type
     * @param messageParam the message param
     * @return the error message from param
     */
    private static String getErrorMessageFromParam(ErrorType errorType, String messageParam) {
        return String.format(errorType.getMessageErrorTemplate(), messageParam);
    }

}
