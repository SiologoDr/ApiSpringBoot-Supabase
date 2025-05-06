package cl.api.buho.proyecto.services;

import cl.api.buho.proyecto.models.Desarrolladores;
import cl.api.buho.proyecto.repositories.IDesarrolladoresRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DesarrolladoresService {

    private final IDesarrolladoresRepository desarrolladoresRepository;

    @Autowired
    public DesarrolladoresService(IDesarrolladoresRepository desarrolladoresRepository) {
        this.desarrolladoresRepository = desarrolladoresRepository;
    }

    // Metodo para crear desarrollador
    public void crearDesarrollador(Desarrolladores desarrollador) {
        Desarrolladores guardado = desarrolladoresRepository.save(desarrollador);
        if (guardado.getId_desarrollador() != null) {
            return;
        } else {
            throw new EntityNotFoundException("Error al agregar un Desarrollador.");
        }
    }

    // Metodo para listar todos los desarrolladores
    public List<Desarrolladores> listarDesarrolladores() {
        List<Desarrolladores> lista = desarrolladoresRepository.findAll();
        if (lista.isEmpty()) {
            throw new EntityNotFoundException("No hay Desarrolladores registrados.");
        }
        return lista;
    }

    // Metodo para buscar desarrollador por ID
    public Desarrolladores buscarPorId(Long id) {
        return desarrolladoresRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Desarrollador con ID: " + id + " no encontrado."));
    }

    // Metodo para actualizar desarrollador por ID
    public void actualizarDesarrollador(Long id, Desarrolladores nuevosDatos) {
        Optional<Desarrolladores> optional = desarrolladoresRepository.findById(id);
        if (optional.isPresent()) {
            Desarrolladores existente = optional.get();

            existente.setNombre(nuevosDatos.getNombre());
            existente.setCorreo(nuevosDatos.getCorreo());
            existente.setTelefono(nuevosDatos.getTelefono());
            existente.setArea_especialidad(nuevosDatos.getArea_especialidad());

            desarrolladoresRepository.save(existente);
        } else {
            throw new EntityNotFoundException("Desarrollador con ID " + id + " no encontrado.");
        }
    }

    // Metodo para eliminar desarrollador por ID
    public void eliminarDesarrollador(Long id) {
        if (desarrolladoresRepository.existsById(id)) {
            desarrolladoresRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Desarrollador con ID " + id + " no encontrado para eliminar.");
        }
    }
}
