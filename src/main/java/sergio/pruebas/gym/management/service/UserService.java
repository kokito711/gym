package sergio.pruebas.gym.management.service;

import org.springframework.stereotype.Service;
import sergio.pruebas.gym.management.entities.dtos.UsuarioDto;

import java.util.List;

@Service
public class UserService {
    public List<UsuarioDto> buscarUsuario(UsuarioDto usuarioDto) {
        return null;
    }

    public Boolean bajaUsuario(Long userId) {
        return null;
    }

    public UsuarioDto altaUsuario(UsuarioDto newUsuario) {
        return null;
    }

    public UsuarioDto modificarUsuario(Long userId, UsuarioDto newInfo) {
        return null;
    }
}
