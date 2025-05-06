package cl.api.buho.proyecto.repositories;

import cl.api.buho.proyecto.models.Estados;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEstadosRepository extends JpaRepository<Estados, Long>{

}
