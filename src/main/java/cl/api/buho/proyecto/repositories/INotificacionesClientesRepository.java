package cl.api.buho.proyecto.repositories;

import cl.api.buho.proyecto.models.NotificacionesClientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface INotificacionesClientesRepository extends JpaRepository<NotificacionesClientes, Long>{

}
