package sergio.pruebas.gym.management.entities.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import java.time.OffsetDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
public class BaseException extends RuntimeException {
    private final OffsetDateTime executionTime = OffsetDateTime.now();

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);

    }

    public BaseException(String message, Throwable cause, boolean enableSuppression,
                         boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
