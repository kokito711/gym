package sergio.pruebas.gym.management.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import sergio.pruebas.gym.management.entities.daos.UsuarioDao;
import sergio.pruebas.gym.management.entities.dtos.UsuarioDto;
import sergio.pruebas.gym.management.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static sergio.pruebas.gym.constants.TEST_VALUE_LONG;
import static sergio.pruebas.gym.constants.TEST_VALUE_STR;

class UserServiceImplTest {

    public static final UsuarioDto EXPECTED_USUARIO_DTO = new UsuarioDto(1L, TEST_VALUE_STR, TEST_VALUE_STR);
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

        when(userRepository.findByDni(TEST_VALUE_STR)).thenReturn(usuarioDao);

        var obtained = userServiceImpl.buscarUsuario(usuario).get(0);

        assertThat(obtained).isEqualTo(EXPECTED_USUARIO_DTO);
        verify(userRepository, times(1)).findByDni(TEST_VALUE_STR);
    }

    @Test
    void buscarUsuarioByNameAndDni() {
        var usuario = new UsuarioDto(null, TEST_VALUE_STR, TEST_VALUE_STR);
        var usuarioDao = new UsuarioDao(TEST_VALUE_LONG, TEST_VALUE_STR, TEST_VALUE_STR);

        when(userRepository.findByNameAndDni(TEST_VALUE_STR, TEST_VALUE_STR)).thenReturn(usuarioDao);

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
    void bajaUsuario() {
        fail();
    }

    @Test
    void altaUsuario() {
        fail();
    }

    @Test
    void modificarUsuario() {
        fail();
    }

//    @Test
//    void shouldThrowExceptionWhenCreateUserAlreadyExists() {
//        var usuarioDto = new UsuarioDto(null, TEST_VALUE_STR, TEST_VALUE_STR);
//
//        var thrown = assertThrows(UserNotFoundException.class,
//                () -> gymManagementUserImpl.altaUsuario(usuarioDto));
//
//        assertTrue(thrown.getMessage().contentEquals("User cannot be found in system"));
//
//        verify(userServiceImpl, times(1)).altaUsuario(usuarioDto);
//    }
}