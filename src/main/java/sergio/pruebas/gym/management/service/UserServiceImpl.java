package sergio.pruebas.gym.management.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sergio.pruebas.gym.management.entities.daos.UsuarioDao;
import sergio.pruebas.gym.management.entities.dtos.UsuarioDto;
import sergio.pruebas.gym.management.entities.exceptions.UserAlreadyExistsException;
import sergio.pruebas.gym.management.repository.UserRepository;

import java.util.List;
import java.util.Objects;

import static org.apache.logging.log4j.util.Strings.isBlank;
import static org.apache.logging.log4j.util.Strings.isNotBlank;
import static org.springframework.transaction.annotation.Isolation.SERIALIZABLE;

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
            return repository.findByDni(usuarioDto.dni()).map(this::toDto).stream().toList();
        }
        if (isBlank(usuarioDto.dni()) && isNotBlank(usuarioDto.name())) {
            return repository.findByName(usuarioDto.name()).stream().map(this::toDto).toList();
        }
        return repository.findByNameAndDni(usuarioDto.name(), usuarioDto.dni())
                .map(this::toDto)
                .stream()
                .toList();
    }

    @Override
    @Transactional(isolation = SERIALIZABLE)
    public Boolean bajaUsuario(Long userId) {
        if (repository.existsById(userId)) {
            repository.deleteById(userId);
            return true;
        }
        return false;
    }

    @Override
    @Transactional(isolation = SERIALIZABLE)
    public UsuarioDto altaUsuario(UsuarioDto newUsuario) {
        if (!repository.existsByDni(newUsuario.dni())) {
            return toDto(repository
                    .saveAndFlush(new UsuarioDao(null, newUsuario.name(), newUsuario.dni())));
        }
        throw new UserAlreadyExistsException("User already created");
    }

    @Override
    public UsuarioDto modificarUsuario(Long userId, UsuarioDto newInfo) {
        return null;
    }

    private UsuarioDto toDto(UsuarioDao usuarioDao) {
        return new UsuarioDto(usuarioDao.getUserId(), usuarioDao.getName(), usuarioDao.getDni());
    }
}
