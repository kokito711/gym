package sergio.pruebas.gym.management.entities.dtos;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record UsuarioDto(Long userId, String name, String dni) {

    @JsonIgnore
    public boolean isEmpty() {
        return userId == null && name == null && dni == null;
    }
}
