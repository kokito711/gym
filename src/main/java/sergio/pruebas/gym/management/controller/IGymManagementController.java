package sergio.pruebas.gym.management.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import sergio.pruebas.gym.management.entities.dtos.UsuarioDto;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/management")
public interface IGymManagementController {

    @PostMapping(value = "/user",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    @ResponseBody
    default UsuarioDto altaUsuario(@RequestBody UsuarioDto user) {
        return null;
    }

    @DeleteMapping(value = "/user/{userId}")
    @ResponseStatus(OK)
    default void bajaUsuario(@PathVariable Long userId) {
    }

    @PutMapping(value = "/user/{userId}",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    @ResponseBody
    default UsuarioDto modificarUsuario(@PathVariable Long userId, @RequestBody UsuarioDto user) {
        return null;
    }

    @GetMapping(value = "/user",
            produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    @ResponseBody
    default List<UsuarioDto> buscarUsuario(@RequestParam(value = "userId", required = false) Long userId,
                                           @RequestParam(value = "name", required = false) String name,
                                           @RequestParam(value = "dni", required = false) String dni) {
        return null;
    }

/*
+ altaClase(ClassDto): ClassDto
+ bajaClase(long): null
            + modificarClase(long, ClassDto): ClassDto
+ mostrarClase(long, string):ClassDto
+ listarClases():List<ClassDto>
+ altaMonitor(MonitorDto): MonitorDto
+ bajaMonitor(long)
+ modificarMonitor(long, MonitorDto):MonitorDto
+ listarMonitor(): List<MonitorDto>
+ mostrarMonitor(long, string)
+ asignarMonitorClase(long,long):null
            + mostrarMonitorClases() : List<MonitorInClassDto>
+ asignarUsuarioClase(long,long):null
            + mostrarUsuariosClase(long, string): List<UsersClassDto>*/

}