package sergio.pruebas.gym.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sergio.pruebas.gym.management.entities.daos.UsuarioDao;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UsuarioDao, Long> {

    List<UsuarioDao> findByName(String name);

    Optional<UsuarioDao> findByDni(String dni);

    Optional<UsuarioDao> findByNameAndDni(String name, String dni);

    Boolean existsByDni(String dni);
}
