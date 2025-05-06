package cl.api.buho.proyecto.repositories;

import cl.api.buho.proyecto.models.Desarrolladores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDesarrolladoresRepository extends JpaRepository<Desarrolladores, Long> {
}
