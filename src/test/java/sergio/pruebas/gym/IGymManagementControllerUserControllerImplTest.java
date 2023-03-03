package sergio.pruebas.gym;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import sergio.pruebas.gym.management.controller.IGymManagementControllerUserControllerImpl;
import sergio.pruebas.gym.management.entities.dtos.UsuarioDto;
import sergio.pruebas.gym.management.entities.exceptions.UserNotFoundException;
import sergio.pruebas.gym.management.entities.exceptions.UserValidationException;
import sergio.pruebas.gym.management.service.UserService;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class IGymManagementControllerUserControllerImplTest {

    public static final String TEST_VALUE_STR = "test";
    public static final Long TEST_VALUE_LONG = 1L;
    @Mock
    private UserService userService;

    @InjectMocks
    private IGymManagementControllerUserControllerImpl gymManagementUserControllerImpl;

    public static Stream<Arguments> GetUsersListArguments() {
        return Stream.of(Arguments.of(null, null, null),
                Arguments.of(TEST_VALUE_LONG, null, null),
                Arguments.of(null, TEST_VALUE_STR, null),
                Arguments.of(null, null, TEST_VALUE_STR),
                Arguments.of(TEST_VALUE_LONG, TEST_VALUE_STR, null),
                Arguments.of(TEST_VALUE_LONG, null, TEST_VALUE_STR),
                Arguments.of(null, TEST_VALUE_STR, TEST_VALUE_STR),
                Arguments.of(TEST_VALUE_LONG, TEST_VALUE_STR, TEST_VALUE_STR)
        );
    }

    public static Stream<Arguments> UsuarioDtoInvalidArguments() {
        return Stream.of(
                Arguments.of(null, null, "Either the name nor the dni cannot be null"),
                Arguments.of(TEST_VALUE_STR, null, "Dni cannot be null"),
                Arguments.of(null, TEST_VALUE_STR, "Name cannot be null"));
    }

    @ParameterizedTest(name = "shouldReturnUserDtoListGetUsersIsCalled_id_{0}_name_{1}_dni_{2}")
    @MethodSource("GetUsersListArguments")
    void shouldReturnUserDtoListGetUsersIsCalled(Long userId, String name, String dni) {
        var usuarioDto = mock(UsuarioDto.class);
        when(userService.buscarUsuario(any(UsuarioDto.class))).thenReturn(List.of(usuarioDto));

        var obtained = gymManagementUserControllerImpl.buscarUsuario(userId, name, dni);

        assertThat(obtained).asList().hasSize(1);
        verify(userService, times(1)).buscarUsuario(any(UsuarioDto.class));
    }

    @Test
    void shouldReturnNullWhenUserNotFound() {
        when(userService.buscarUsuario(any(UsuarioDto.class))).thenReturn(Collections.emptyList());

        var thrown = assertThrows(UserNotFoundException.class,
                () -> gymManagementUserControllerImpl.buscarUsuario(null, TEST_VALUE_STR, null));

        assertTrue(thrown.getMessage().contentEquals("User cannot be found in system"));
    }

    @Test
    void shouldFinishRightWhenDeleteUserIsCalled() {
        when(userService.bajaUsuario(TEST_VALUE_LONG)).thenReturn(true);

        gymManagementUserControllerImpl.bajaUsuario(TEST_VALUE_LONG);

        verify(userService, times(1)).bajaUsuario(TEST_VALUE_LONG);
    }

    @Test
    void shouldThrowExceptionWhenDeleteUserNotExists() {
        when(userService.bajaUsuario(TEST_VALUE_LONG)).thenReturn(false);

        var thrown = assertThrows(UserNotFoundException.class, () -> gymManagementUserControllerImpl.bajaUsuario(TEST_VALUE_LONG));

        assertTrue(thrown.getMessage().contentEquals("User cannot be found in system"));
    }

    @Test
    void shouldReturnUserDtoWhenCreateUserIsCalled() {
        var usuarioDto = new UsuarioDto(null, TEST_VALUE_STR, TEST_VALUE_STR);
        when(userService.altaUsuario(any(UsuarioDto.class))).thenReturn(usuarioDto);

        var obtained = gymManagementUserControllerImpl.altaUsuario(usuarioDto);

        assertNotNull(obtained);
        verify(userService, times(1)).altaUsuario(usuarioDto);
    }

    @ParameterizedTest(name = "shouldThrowExceptionWhenCreateUserValidationFails_name_{0}_dni_{1}")
    @MethodSource("UsuarioDtoInvalidArguments")
    void shouldThrowExceptionWhenCreateUserValidationFails(String name, String dni, String message) {
        var usuarioDto = new UsuarioDto(null, name, dni);

        var thrown = assertThrows(UserValidationException.class,
                () -> gymManagementUserControllerImpl.altaUsuario(usuarioDto));

        assertTrue(thrown.getMessage().contentEquals(message));

        verify(userService, never()).altaUsuario(usuarioDto);
    }

/*    @Test
    void shouldThrowExceptionWhenCreateUserAlreadyExists() {
        var usuarioDto = new UsuarioDto(null, TEST_VALUE_STR, TEST_VALUE_STR);

        var thrown = assertThrows(UserNotFoundException.class,
                () -> gymManagementUserImpl.altaUsuario(usuarioDto));

        assertTrue(thrown.getMessage().contentEquals("User cannot be found in system"));

        verify(userService, times(1)).altaUsuario(usuarioDto);
    }*/

    @Test
    void shouldReturnUserDtoWhenUpdateUserIsCalled() {
        var usuarioDto = new UsuarioDto(null, TEST_VALUE_STR, TEST_VALUE_STR);
        when(userService.modificarUsuario(any(Long.class), any(UsuarioDto.class))).thenReturn(usuarioDto);

        var obtained = gymManagementUserControllerImpl.modificarUsuario(TEST_VALUE_LONG, usuarioDto);

        assertNotNull(obtained);
        verify(userService, times(1)).modificarUsuario(TEST_VALUE_LONG, usuarioDto);
    }

    @ParameterizedTest(name = "shouldThrowExceptionWhenUpdateUserValidationFails_name_{0}_dni_{1}")
    @MethodSource("UsuarioDtoInvalidArguments")
    void shouldThrowExceptionWhenUpdateUserValidationFails(String name, String dni, String message) {
        var usuarioDto = new UsuarioDto(null, name, dni);

        var thrown = assertThrows(UserValidationException.class,
                () -> gymManagementUserControllerImpl.modificarUsuario(TEST_VALUE_LONG, usuarioDto));

        assertTrue(thrown.getMessage().contentEquals(message));

        verify(userService, never()).modificarUsuario(TEST_VALUE_LONG, usuarioDto);
    }
}
