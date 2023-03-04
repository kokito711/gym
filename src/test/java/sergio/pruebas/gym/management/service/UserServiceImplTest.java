package sergio.pruebas.gym.management.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Test
    void buscarUsuario() {
    }

    @Test
    void bajaUsuario() {
    }

    @Test
    void altaUsuario() {
    }

    @Test
    void modificarUsuario() {
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