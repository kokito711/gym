package sergio.pruebas.gym;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import sergio.pruebas.gym.management.controller.GymManagementImpl;
import sergio.pruebas.gym.management.entities.dtos.UsuarioDto;
import sergio.pruebas.gym.management.service.UserService;

import java.net.http.HttpResponse;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.times;
import static org.springframework.http.HttpStatus.OK;

@SpringBootTest
public class GymManagementImplTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private GymManagementImpl gymManagementImpl;


    @Test
    void shouldReturnUserDtoListGetUsersIsCalled() {
        HttpResponse<List<UsuarioDto>> obtained = (HttpResponse<List<UsuarioDto>>) gymManagementImpl
                .buscarUsuario(null, null, null);

        assertThat(obtained.body()).asList().hasSize(1);
        assertEquals(obtained.statusCode(), OK.value());

        Mockito.verify(userService.getAllUsers(), times(1));
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
