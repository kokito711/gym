package sergio.pruebas.gym;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import sergio.pruebas.gym.management.controller.GymManagementImpl;
import sergio.pruebas.gym.management.entities.dtos.UsuarioDto;
import sergio.pruebas.gym.management.service.UserService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@SpringBootTest
public class GymManagementImplTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private GymManagementImpl gymManagementImpl;


    @Test
    void shouldReturnUserDtoListGetUsersIsCalled() {
        when(userService.getAllUsers(mock(UsuarioDto.class))).thenReturn(List.of(mock(UsuarioDto.class)));

        List<UsuarioDto> obtained = gymManagementImpl.buscarUsuario(null, "test", null);

        assertThat(obtained).asList().hasSize(1);
        Mockito.verify(userService.getAllUsers(any(UsuarioDto.class)), times(1));
    }

    @Test
    void shouldReturnUserDtoWhenCreateUserIsCalled() {
        fail();
    }

    @Test
    void shouldThrowExceptionWhenCreateUserAlreadyExists() {
        fail();
    }

    @Test
    void shouldReturnUserDtoWhenUUIDIsGivenAndGetUserIsCalled() {
        fail();
    }

    @Test
    void shouldReturnUserDtoWhenNameIsGivenAndGetUserIsCalled() {
        fail();
    }

    @Test
    void shouldReturnUserDtoWhenDniIsGivenAndGetUserIsCalled() {
        fail();
    }

    @Test
    void shouldReturnNullWhenUserNotFound() {
        fail();
    }

    @Test
    void shouldReturnNullWhenDeleteUserIsCalled() {
        fail();
    }

    @Test
    void shouldThrowExceptionWhenDeleteUserNotExists() {
        fail();
    }

    @Test
    void shouldReturnUserDtoWhenUpdateUserIsCalled() {
        fail();
    }

    @Test
    void shouldThrowExceptionWhenUpdateUserAlreadyExists() {
        fail();
    }
}
