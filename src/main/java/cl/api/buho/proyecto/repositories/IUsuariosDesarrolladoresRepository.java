package cl.api.buho.proyecto.repositories;

import cl.api.buho.proyecto.models.UsuariosDesarrolladores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuariosDesarrolladoresRepository extends JpaRepository<UsuariosDesarrolladores, Long>{

}
