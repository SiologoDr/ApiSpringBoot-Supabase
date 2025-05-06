package cl.api.buho.proyecto.services;

import cl.api.buho.proyecto.models.EstadosRevisiones;
import cl.api.buho.proyecto.repositories.IEstadosRevisionesRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstadosRevisionesService {

    private final IEstadosRevisionesRepository estadosRevisionesRepository;

    @Autowired
    public EstadosRevisionesService(IEstadosRevisionesRepository estadosRevisionesRepository) {
        this.estadosRevisionesRepository = estadosRevisionesRepository;
    }

    // Metodo para crear estado de revisión
    public void crearEstadoRevision(EstadosRevisiones estado) {
        EstadosRevisiones guardado = estadosRevisionesRepository.save(estado);
        if (guardado.getId_estado_revision() == null) {
            throw new EntityNotFoundException("Error al agregar un Estado de Revisión.");
        }
    }

    // Metodo para listar todos los estados de revisión
    public List<EstadosRevisiones> listarEstadosRevisiones() {
        List<EstadosRevisiones> lista = estadosRevisionesRepository.findAll();
        if (lista.isEmpty()) {
            throw new EntityNotFoundException("No hay Estados de Revisión registrados.");
        }
        return lista;
    }

    // Metodo para buscar estado de revisión por ID
    public EstadosRevisiones buscarPorId(Long id) {
        return estadosRevisionesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Estado de Revisión con ID: " + id + " no encontrado."));
    }

    // Metodo para actualizar estado de revisión por ID
    public void actualizarEstadoRevision(Long id, EstadosRevisiones nuevosDatos) {
        Optional<EstadosRevisiones> optional = estadosRevisionesRepository.findById(id);
        if (optional.isPresent()) {
            EstadosRevisiones existente = optional.get();

            existente.setNombre(nuevosDatos.getNombre());
            existente.setDescripcion(nuevosDatos.getDescripcion());

            estadosRevisionesRepository.save(existente);
        } else {
            throw new EntityNotFoundException("Estado de Revisión con ID " + id + " no encontrado.");
        }
    }

    // Metodo para eliminar estado de revisión por ID
    public void eliminarEstadoRevision(Long id) {
        if (estadosRevisionesRepository.existsById(id)) {
            estadosRevisionesRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Estado de Revisión con ID " + id + " no encontrado para eliminar.");
        }
    }
}
