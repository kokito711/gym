package sergio.pruebas.gym.management.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import sergio.pruebas.gym.management.entities.dtos.ErrorDto;
import sergio.pruebas.gym.management.entities.exceptions.UserNotFoundException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Log4j2
@ControllerAdvice
public class GymManagementControllerAdvice {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    public ErrorDto handleErrorNotFound(UserNotFoundException exception) {
        log.warn("User cannot be found in system");
        return new ErrorDto(exception.getMessage(), exception.getExecutionTime());
    }

}
