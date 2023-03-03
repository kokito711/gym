package sergio.pruebas.gym.management.controller;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import sergio.pruebas.gym.management.entities.dtos.UsuarioDto;
import sergio.pruebas.gym.management.entities.exceptions.UserNotFoundException;
import sergio.pruebas.gym.management.entities.exceptions.UserValidationException;
import sergio.pruebas.gym.management.service.UserService;

import java.util.List;

import static org.apache.logging.log4j.util.Strings.isBlank;

@AllArgsConstructor
@Log4j2
public class GymManagementUserControllerImpl implements GymManagement {

    @Autowired
    private final UserService userService;

    @Override
    public List<UsuarioDto> buscarUsuario(Long userId, String name, String dni) {
        var users = userService.buscarUsuario(new UsuarioDto(userId, name, dni));
        if (users.isEmpty()) {
            throw new UserNotFoundException("User cannot be found in system");
        }
        return users;
    }

    @Override
    public UsuarioDto altaUsuario(UsuarioDto user) {
        validateUser(user);
        return userService.altaUsuario(user);
    }

    @Override
    public void bajaUsuario(Long userId) {
        if (!userService.bajaUsuario(userId)) {
            throw new UserNotFoundException("User cannot be found in system");
        }
    }

    @Override
    public UsuarioDto modificarUsuario(Long userId, UsuarioDto user) {
        validateUser(user);
        return userService.modificarUsuario(userId, user);
    }

    private void validateUser(UsuarioDto user) {
        if (isBlank(user.name()) && isBlank(user.dni())) {
            throw new UserValidationException("Either the name nor the dni cannot be null");
        }
        if (isBlank(user.name()) && !isBlank(user.dni())) {
            throw new UserValidationException("Name cannot be null");
        }
        if (!isBlank(user.name()) && isBlank(user.dni())) {
            throw new UserValidationException("Dni cannot be null");
        }
    }
}
