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
import sergio.pruebas.gym.management.entities.dtos.ClassDto;
import sergio.pruebas.gym.management.entities.dtos.MonitorDto;
import sergio.pruebas.gym.management.entities.dtos.UsuarioDto;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
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
    @ResponseStatus(NO_CONTENT)
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

    @PostMapping(value = "/class",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    @ResponseBody
    default ClassDto altaClase(@RequestBody ClassDto classDto) {
        return null;
    }

    @PutMapping(value = "/class",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    @ResponseBody
    default ClassDto modificarClase(@RequestBody ClassDto classDto) {
        return null;
    }

    @GetMapping(value = "/class",
            produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    @ResponseBody
    default List<ClassDto> mostrarClases(@RequestParam(value = "classId", required = false) Long classId,
                                         @RequestParam(value = "name", required = false) String name) {
        return null;
    }

    @DeleteMapping(value = "/class/{classId}")
    @ResponseStatus(NO_CONTENT)
    @ResponseBody
    default ClassDto bajaClase(@PathVariable Long classId) {
        return null;
    }

    @PostMapping(value = "/monitor",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    @ResponseBody
    default MonitorDto altaMonitor(@RequestBody MonitorDto monitorDto) {
        return null;
    }

    @PostMapping(value = "/monitor:assign",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    @ResponseBody
    default void asignarMonitorClase(@RequestParam(value = "monitorId") Long monitorId,
                                     @RequestParam(value = "classId") Long classId) {
    }

    @PutMapping(value = "/monitor/{monitorId}",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    @ResponseBody
    default MonitorDto modificarMonitor(@PathVariable Long monitorId, @RequestBody MonitorDto monitorDto) {
        return null;
    }

    @GetMapping(value = "/monitor",
            produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    @ResponseBody
    default List<MonitorDto> mostrarMonitores(@RequestParam(value = "monitorId", required = false) Long monitorId,
                                              @RequestParam(value = "name", required = false) String name) {
        return null;
    }

    @DeleteMapping(value = "/monitor/{monitorId}")
    @ResponseStatus(NO_CONTENT)
    @ResponseBody
    default MonitorDto bajaMonitor(@PathVariable Long monitorId) {
        return null;
    }


/*
+ mostrarMonitorClases() : List<MonitorInClassDto>
+ asignarUsuarioClase(long,long):null
 + mostrarUsuariosClase(long, string): List<UsersClassDto>*/

}
