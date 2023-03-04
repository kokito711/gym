package sergio.pruebas.gym.management.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sergio.pruebas.gym.management.entities.daos.UsuarioDao;
import sergio.pruebas.gym.management.entities.dtos.UsuarioDto;
import sergio.pruebas.gym.management.repository.UserRepository;

import java.util.List;
import java.util.Objects;

import static java.util.Collections.emptyList;
import static org.apache.logging.log4j.util.Strings.isBlank;
import static org.apache.logging.log4j.util.Strings.isNotBlank;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository repository;

    @Override
    public List<UsuarioDto> buscarUsuario(UsuarioDto usuarioDto) {
        if (Objects.nonNull(usuarioDto.userId())) {
            var usuarioDao = repository.findById(usuarioDto.userId());
            return usuarioDao.map(this::toDto).stream().toList();
        }
        if (isNotBlank(usuarioDto.dni()) && isBlank(usuarioDto.name())) {
            return List.of(toDto(repository.findByDni(usuarioDto.dni())));
        }
        if (isBlank(usuarioDto.dni()) && isNotBlank(usuarioDto.name())) {
            return repository.findByName(usuarioDto.name()).stream().map(this::toDto).toList();
        }
        if (isNotBlank(usuarioDto.dni()) && isNotBlank(usuarioDto.name())) {
            return List.of(toDto(repository.findByNameAndDni(usuarioDto.name(), usuarioDto.dni())));
        }
        return emptyList();
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

    private UsuarioDto toDto(UsuarioDao usuarioDao) {
        return new UsuarioDto(usuarioDao.getUserId(), usuarioDao.getName(), usuarioDao.getDni());
    }
}
