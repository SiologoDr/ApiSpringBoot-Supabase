package cl.api.buho.proyecto.services;

import cl.api.buho.proyecto.models.Estados;
import cl.api.buho.proyecto.repositories.IEstadosRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstadosService {

    private final IEstadosRepository estadosRepository;

    @Autowired
    public EstadosService(IEstadosRepository estadosRepository) {
        this.estadosRepository = estadosRepository;
    }

    // Metodo para crear estado
    public void crearEstado(Estados estado) {
        Estados guardado = estadosRepository.save(estado);
        if (guardado.getId_estado() != null) {
            return;
        } else {
            throw new EntityNotFoundException("Error al agregar un Estado.");
        }
    }

    // Metodo para listar todos los estados
    public List<Estados> listarEstados() {
        List<Estados> lista = estadosRepository.findAll();
        if (lista.isEmpty()) {
            throw new EntityNotFoundException("No hay Estados registrados.");
        }
        return lista;
    }

    // Metodo para buscar estado por ID
    public Estados buscarPorId(Long id) {
        return estadosRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Estado con ID: " + id + " no encontrado."));
    }

    // Metodo para actualizar estado por ID
    public void actualizarEstado(Long id, Estados nuevosDatos) {
        Optional<Estados> optional = estadosRepository.findById(id);
        if (optional.isPresent()) {
            Estados existente = optional.get();

            existente.setNombre(nuevosDatos.getNombre());
            existente.setDescripcion(nuevosDatos.getDescripcion());

            estadosRepository.save(existente);
        } else {
            throw new EntityNotFoundException("Estado con ID " + id + " no encontrado.");
        }
    }

    // Metodo para eliminar estado por ID
    public void eliminarEstado(Long id) {
        if (estadosRepository.existsById(id)) {
            estadosRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Estado con ID " + id + " no encontrado para eliminar.");
        }
    }
}
