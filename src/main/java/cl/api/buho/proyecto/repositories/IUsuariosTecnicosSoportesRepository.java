package cl.api.buho.proyecto.repositories;

import cl.api.buho.proyecto.models.UsuariosTecnicosSoportes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuariosTecnicosSoportesRepository extends JpaRepository<UsuariosTecnicosSoportes, Long>{

}
