package sergio.pruebas.gym.management.entities.daos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "users", schema = "gym")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
