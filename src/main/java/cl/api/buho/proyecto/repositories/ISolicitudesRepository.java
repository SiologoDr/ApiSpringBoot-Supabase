package cl.api.buho.proyecto.repositories;

import cl.api.buho.proyecto.models.Solicitudes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISolicitudesRepository extends JpaRepository<Solicitudes, Long>{

}
