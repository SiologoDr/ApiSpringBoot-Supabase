package cl.api.buho.proyecto.repositories;

import cl.api.buho.proyecto.models.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuariosRepository extends JpaRepository<Usuarios, Long> {

}
