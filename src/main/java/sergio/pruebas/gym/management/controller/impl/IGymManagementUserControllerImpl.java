package sergio.pruebas.gym.management.controller.impl;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import sergio.pruebas.gym.management.controller.IGymManagementController;
import sergio.pruebas.gym.management.entities.dtos.UsuarioDto;
import sergio.pruebas.gym.management.entities.exceptions.UserNotFoundException;
import sergio.pruebas.gym.management.entities.exceptions.UserValidationException;
import sergio.pruebas.gym.management.service.UserService;

import java.util.List;

import static org.apache.logging.log4j.util.Strings.isBlank;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@AllArgsConstructor
@Log4j2

@RestController
@RequestMapping("/management")
public class IGymManagementUserControllerImpl implements IGymManagementController {

    @Autowired
    private final UserService userService;

    @GetMapping(value = "/user",
            produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    @ResponseBody
    @Override
    public List<UsuarioDto> buscarUsuario(@RequestParam(value = "userId", required = false) Long userId,
                                          @RequestParam(value = "name", required = false) String name,
                                          @RequestParam(value = "dni", required = false) String dni) {
        var users = userService.buscarUsuario(new UsuarioDto(userId, name, dni));
        if (users.isEmpty()) {
            throw new UserNotFoundException("User cannot be found in system");
        }
        return users;
    }

    @PostMapping(value = "/user",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    @ResponseBody
    @Override
    public UsuarioDto altaUsuario(@RequestBody UsuarioDto user) {
        validateCreateUser(user);
        return userService.altaUsuario(user);
    }

    @DeleteMapping(value = "/user/{userId}")
    @ResponseStatus(OK)
    @Override
    public void bajaUsuario(@PathVariable Long userId) {
        if (!userService.bajaUsuario(userId)) {
            throw new UserNotFoundException("User cannot be found in system");
        }
    }

    @PutMapping(value = "/user/{userId}",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    @ResponseBody
    @Override
    public UsuarioDto modificarUsuario(@PathVariable Long userId, @RequestBody UsuarioDto user) {
        validateCommonUser(user);
        return userService.modificarUsuario(userId, user);
    }

    private void validateCreateUser(UsuarioDto user) {
        validateCommonUser(user);
        if (!isBlank(user.name()) && isBlank(user.dni())) {
            throw new UserValidationException("Dni cannot be null");
        }
    }

    private void validateCommonUser(UsuarioDto user) {
        if (isBlank(user.name()) && isBlank(user.dni())) {
            throw new UserValidationException("Either the name nor the dni cannot be null");
        }
        if (isBlank(user.name()) && !isBlank(user.dni())) {
            throw new UserValidationException("Name cannot be null");
        }
    }
}
