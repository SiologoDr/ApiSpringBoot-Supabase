package cl.api.buho.proyecto.repositories;

import cl.api.buho.proyecto.models.NotificacionesDesarrolladores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface INotificacionesDesarrolladoresRepository extends JpaRepository<NotificacionesDesarrolladores, Long>{

}
