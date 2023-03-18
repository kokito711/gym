package sergio.pruebas.gym.management.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sergio.pruebas.gym.management.entities.daos.UsuarioDao;
import sergio.pruebas.gym.management.entities.dtos.UsuarioDto;
import sergio.pruebas.gym.management.entities.exceptions.UserAlreadyExistsException;
import sergio.pruebas.gym.management.entities.exceptions.UserNotFoundException;
import sergio.pruebas.gym.management.repository.UserRepository;
import sergio.pruebas.gym.management.service.UserService;

import java.util.List;

import static java.util.Objects.nonNull;
import static org.apache.logging.log4j.util.Strings.isBlank;
import static org.apache.logging.log4j.util.Strings.isNotBlank;
import static org.springframework.transaction.annotation.Isolation.SERIALIZABLE;

@Service
@RequiredArgsConstructor
@EnableCaching
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository repository;

    @Override
    @Cacheable("users")
    public List<UsuarioDto> buscarUsuario(UsuarioDto usuarioDto) {
        if (usuarioDto.isEmpty()) {
            return repository.findAll().stream().map(this::toDto).toList();
        }
        if (nonNull(usuarioDto.userId())) {
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
    @CacheEvict(cacheNames = "users", key = "usuarioDto.userId")
    public Boolean bajaUsuario(Long userId) {
        if (repository.existsById(userId)) {
            repository.deleteById(userId);
            return true;
        }
        return false;
    }

    @Override
    @Transactional(isolation = SERIALIZABLE)
    @CachePut(value = "users")
    public UsuarioDto altaUsuario(UsuarioDto newUsuario) {
        if (!repository.existsByDni(newUsuario.dni())) {
            return toDto(repository
                    .saveAndFlush(new UsuarioDao(null, newUsuario.name(), newUsuario.dni())));
        }
        throw new UserAlreadyExistsException("User already created");
    }

    @Override
    @CachePut(value = "users")
    public UsuarioDto modificarUsuario(final Long userId, final UsuarioDto newInfo) {
        if (!repository.existsById(userId)) {
            throw new UserNotFoundException("User cannot be found in system");
        }
        var savedUser = repository.getReferenceById(userId);
        if (isNotBlank(newInfo.name())) {
            savedUser.setName(newInfo.name());
        }
        if (isNotBlank(newInfo.dni())) {
            savedUser.setDni(newInfo.dni());
        }
        return toDto(repository.saveAndFlush(savedUser));
    }

    private UsuarioDto toDto(UsuarioDao usuarioDao) {
        return new UsuarioDto(usuarioDao.getUserId(), usuarioDao.getName(), usuarioDao.getDni());
    }
}
