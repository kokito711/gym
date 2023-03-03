package sergio.pruebas.gym.management.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import sergio.pruebas.gym.management.entities.dtos.ErrorDto;
import sergio.pruebas.gym.management.entities.exceptions.UserNotFoundException;
import sergio.pruebas.gym.management.entities.exceptions.UserValidationException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Log4j2
@ControllerAdvice
public class GymManagementControllerAdvice {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    public ErrorDto handleErrorNotFound(UserNotFoundException exception) {
        log.warn(exception.getMessage());
        return new ErrorDto(exception.getMessage(), exception.getExecutionTime());
    }

    @ExceptionHandler(UserValidationException.class)
    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    public ErrorDto handleErrorUserValidation(UserValidationException exception) {
        log.warn(exception.getMessage());
        return new ErrorDto(exception.getMessage(), exception.getExecutionTime());
    }
}
