package com.padwicki.webclinic.CustomExceptions;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import com.padwicki.webclinic.ControllerAdvice.AdviceException;

/**
 * The class, which will be wrapped in {@link ResponseEntity} and given in this {@link AdviceException}.
 */
@Getter
@Setter
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
}
