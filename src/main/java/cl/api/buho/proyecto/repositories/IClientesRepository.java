package cl.api.buho.proyecto.repositories;

import cl.api.buho.proyecto.models.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClientesRepository extends JpaRepository<Clientes, Long>{

}
