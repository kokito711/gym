package sergio.pruebas.gym.management.service;

import org.springframework.stereotype.Service;
import sergio.pruebas.gym.management.entities.dtos.UsuarioDto;

import java.util.List;

@Service
public interface UserService {
    List<UsuarioDto> buscarUsuario(UsuarioDto usuarioDto);

    Boolean bajaUsuario(Long userId);

    UsuarioDto altaUsuario(UsuarioDto newUsuario);

    UsuarioDto modificarUsuario(Long userId, UsuarioDto newInfo);
}
