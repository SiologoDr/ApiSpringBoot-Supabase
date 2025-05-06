package cl.api.buho.proyecto.repositories;

import cl.api.buho.proyecto.models.UsuariosClientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuariosClientesRepository extends JpaRepository<UsuariosClientes, Long>{

}
