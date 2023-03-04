package sergio.pruebas.gym.management.service;

import org.springframework.stereotype.Service;
import sergio.pruebas.gym.management.entities.dtos.UsuarioDto;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public List<UsuarioDto> buscarUsuario(UsuarioDto usuarioDto) {
        return null;
    }

    @Override
    public Boolean bajaUsuario(Long userId) {
        return null;
    }

    @Override
    public UsuarioDto altaUsuario(UsuarioDto newUsuario) {
        return null;
    }

    @Override
    public UsuarioDto modificarUsuario(Long userId, UsuarioDto newInfo) {
        return null;
    }
}
