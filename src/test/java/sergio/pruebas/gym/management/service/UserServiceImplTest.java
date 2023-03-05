package sergio.pruebas.gym.management.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import sergio.pruebas.gym.management.entities.daos.UsuarioDao;
import sergio.pruebas.gym.management.entities.dtos.UsuarioDto;
import sergio.pruebas.gym.management.entities.exceptions.UserAlreadyExistsException;
import sergio.pruebas.gym.management.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static sergio.pruebas.gym.constants.TEST_VALUE_LONG;
import static sergio.pruebas.gym.constants.TEST_VALUE_STR;

@SpringBootTest
class UserServiceImplTest {

    public static final UsuarioDto EXPECTED_USUARIO_DTO = new UsuarioDto(TEST_VALUE_LONG, TEST_VALUE_STR, TEST_VALUE_STR);
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Test
    void buscarUsuarioByName() {
        var usuario = new UsuarioDto(null, TEST_VALUE_STR, null);
        var usuarioDao = new UsuarioDao(TEST_VALUE_LONG, TEST_VALUE_STR, TEST_VALUE_STR);

        when(userRepository.findByName(TEST_VALUE_STR)).thenReturn(List.of(usuarioDao));

        var obtained = userServiceImpl.buscarUsuario(usuario).get(0);

        assertThat(obtained).isEqualTo(EXPECTED_USUARIO_DTO);
        verify(userRepository, times(1)).findByName(TEST_VALUE_STR);
    }

    @Test
    void buscarUsuarioByDni() {
        var usuario = new UsuarioDto(null, null, TEST_VALUE_STR);
        var usuarioDao = new UsuarioDao(TEST_VALUE_LONG, TEST_VALUE_STR, TEST_VALUE_STR);

        when(userRepository.findByDni(TEST_VALUE_STR)).thenReturn(Optional.of(usuarioDao));

        var obtained = userServiceImpl.buscarUsuario(usuario).get(0);

        assertThat(obtained).isEqualTo(EXPECTED_USUARIO_DTO);
        verify(userRepository, times(1)).findByDni(TEST_VALUE_STR);
    }

    @Test
    void buscarUsuarioByNameAndDni() {
        var usuario = new UsuarioDto(null, TEST_VALUE_STR, TEST_VALUE_STR);
        var usuarioDao = new UsuarioDao(TEST_VALUE_LONG, TEST_VALUE_STR, TEST_VALUE_STR);

        when(userRepository.findByNameAndDni(TEST_VALUE_STR, TEST_VALUE_STR)).thenReturn(Optional.of(usuarioDao));

        var obtained = userServiceImpl.buscarUsuario(usuario).get(0);

        assertThat(obtained).isEqualTo(EXPECTED_USUARIO_DTO);
        verify(userRepository, times(1)).findByNameAndDni(TEST_VALUE_STR, TEST_VALUE_STR);
    }

    @Test
    void buscarUsuarioById() {
        var usuario = new UsuarioDto(TEST_VALUE_LONG, null, null);
        var usuarioDao = new UsuarioDao(TEST_VALUE_LONG, TEST_VALUE_STR, TEST_VALUE_STR);

        when(userRepository.findById(TEST_VALUE_LONG)).thenReturn(Optional.of(usuarioDao));

        var obtained = userServiceImpl.buscarUsuario(usuario).get(0);

        assertThat(obtained).isEqualTo(EXPECTED_USUARIO_DTO);
        verify(userRepository, times(1)).findById(TEST_VALUE_LONG);
    }

    @Test
    void buscarUsuarioReturnsEmptyListWhenRepoDoesNotContainAnything() {
        var usuario = new UsuarioDto(null, null, TEST_VALUE_STR);

        when(userRepository.findByDni(anyString())).thenReturn(Optional.empty());

        var obtained = userServiceImpl.buscarUsuario(usuario);

        assertThat(obtained).isEmpty();
        verify(userRepository, times(1)).findByDni(TEST_VALUE_STR);
    }

    @Test
    void bajaUsuarioReturnsTrueWhenUserDeleted() {
        when(userRepository.existsById(TEST_VALUE_LONG)).thenReturn(true);

        var obtained = userServiceImpl.bajaUsuario(TEST_VALUE_LONG);

        assertTrue(obtained);
        verify(userRepository, times(1)).existsById(TEST_VALUE_LONG);
        verify(userRepository, times(1)).deleteById(TEST_VALUE_LONG);
    }

    @Test
    void bajaUsuarioReturnsFalseWhenUserNotDeleted() {
        when(userRepository.existsById(TEST_VALUE_LONG)).thenReturn(false);

        var obtained = userServiceImpl.bajaUsuario(TEST_VALUE_LONG);

        assertFalse(obtained);
        verify(userRepository, times(1)).existsById(TEST_VALUE_LONG);
        verify(userRepository, never()).deleteById(TEST_VALUE_LONG);
    }

    @Test
    void altaUsuarioRetornaUsuarioCreado() {
        var usuario = new UsuarioDto(null, TEST_VALUE_STR, TEST_VALUE_STR);
        var savedUsuarioDao = new UsuarioDao(TEST_VALUE_LONG, TEST_VALUE_STR, TEST_VALUE_STR);
        var usuarioDao = new UsuarioDao(null, TEST_VALUE_STR, TEST_VALUE_STR);

        when(userRepository.existsByDni(usuarioDao.getDni())).thenReturn(false);
        when(userRepository.saveAndFlush(usuarioDao)).thenReturn(savedUsuarioDao);

        var obtained = userServiceImpl.altaUsuario(usuario);

        assertThat(obtained).isEqualTo(EXPECTED_USUARIO_DTO);
        verify(userRepository, times(1)).existsByDni(usuarioDao.getDni());
        verify(userRepository, times(1)).saveAndFlush(usuarioDao);
    }

    @Test
    void shouldThrowExceptionWhenCreateUserAlreadyExists() {
        var usuarioDto = new UsuarioDto(null, TEST_VALUE_STR, TEST_VALUE_STR);

        when(userRepository.existsByDni(TEST_VALUE_STR)).thenReturn(true);

        var thrown = assertThrows(UserAlreadyExistsException.class,
                () -> userServiceImpl.altaUsuario(usuarioDto));

        assertTrue(thrown.getMessage().contentEquals("User already created"));
        verify(userRepository, times(1)).existsByDni(TEST_VALUE_STR);
        verify(userRepository, never()).saveAndFlush(any(UsuarioDao.class));
    }

    @Test
    void modificarUsuario() {
        fail();
    }


}