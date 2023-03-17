package sergio.pruebas.gym.management.entities.daos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
public class UsuarioDao {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long userId;

    @Column(name = "name", nullable = false)
    @Length(max = 512)
    @NotBlank
    private String name;

    @Column(name = "dni", nullable = false)
    @NotBlank
    @Length(min = 9, max = 9)
    private String dni;
}
