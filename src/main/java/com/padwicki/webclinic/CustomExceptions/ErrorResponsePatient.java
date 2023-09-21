package com.padwicki.webclinic.CustomExceptions;
import org.springframework.http.ResponseEntity;
import com.padwicki.webclinic.ControllerAdvice.AdviceException;

/**
 * The class, which will be wrapped in {@link ResponseEntity} and given in this {@link AdviceException}.
 */
public class ErrorResponsePatient {
    /**
     * Class field that will contain the error message.
     */
    String message;

    /**
     * Class constructor.
     * @param message error message.
     */
    public ErrorResponsePatient(String message) {
        this.message = message;
    }

    /**
     * Getter of {@link ErrorResponsePatient}.
     * @return error message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Setter of {@link ErrorResponsePatient}.
     * @param message error message.
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
