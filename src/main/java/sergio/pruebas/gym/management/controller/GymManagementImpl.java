package sergio.pruebas.gym.management.controller;

import lombok.AllArgsConstructor;
import sergio.pruebas.gym.management.entities.dtos.UsuarioDto;

import java.net.http.HttpResponse;
import java.util.UUID;

@AllArgsConstructor
public class GymManagementImpl implements GymManagement {


    @Override
    public HttpResponse<?> buscarUsuario(UUID userId, String name, String dni) {
        return null;
    }

    @Override
    public HttpResponse<?> altaUsuario(UsuarioDto user) {
        return null;
    }

    @Override
    public HttpResponse<?> bajaUsuario(UUID userId) {
        return null;
    }

    @Override
    public HttpResponse<?> modificarUsuario(UUID userId, UsuarioDto user) {
        return null;
    }
}
