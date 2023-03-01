package sergio.pruebas.gym.management.controller;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import sergio.pruebas.gym.management.entities.dtos.UsuarioDto;
import sergio.pruebas.gym.management.entities.exceptions.UserNotFoundException;
import sergio.pruebas.gym.management.service.UserService;

import java.util.List;

@AllArgsConstructor
@Log4j2
public class GymManagementImpl implements GymManagement {

    @Autowired
    private final UserService userService;

    @Override
    public List<UsuarioDto> buscarUsuario(Long userId, String name, String dni) {
        var users = userService.getAllUsers(new UsuarioDto(userId, name, dni));
        if (users.isEmpty()) {
            throw new UserNotFoundException("User cannot be found in system");
        }
        return users;
    }

    @Override
    public ResponseEntity<?> altaUsuario(UsuarioDto user) {
        return null;
    }

    @Override
    public ResponseEntity<?> bajaUsuario(Long userId) {
        return null;
    }

    @Override
    public ResponseEntity<?> modificarUsuario(Long userId, UsuarioDto user) {
        return null;
    }
}
