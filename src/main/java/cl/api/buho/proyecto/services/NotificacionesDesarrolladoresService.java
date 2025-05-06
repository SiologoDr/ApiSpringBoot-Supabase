package cl.api.buho.proyecto.services;

import cl.api.buho.proyecto.models.NotificacionesDesarrolladores;
import cl.api.buho.proyecto.repositories.INotificacionesDesarrolladoresRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificacionesDesarrolladoresService {

    private final INotificacionesDesarrolladoresRepository repository;

    @Autowired
    public NotificacionesDesarrolladoresService(INotificacionesDesarrolladoresRepository repository) {
        this.repository = repository;
    }

    // Crear notificación
    public void crearNotificacion(NotificacionesDesarrolladores notificacion) {
        NotificacionesDesarrolladores guardada = repository.save(notificacion);
        if (guardada.getId_notificacion() == null) {
            throw new EntityNotFoundException("Error al crear la notificación.");
        }
    }

    // Listar todas las notificaciones
    public List<NotificacionesDesarrolladores> listarNotificaciones() {
        List<NotificacionesDesarrolladores> lista = repository.findAll();
        if (lista.isEmpty()) {
            throw new EntityNotFoundException("No hay notificaciones registradas.");
        }
        return lista;
    }

    // Buscar notificación por ID
    public NotificacionesDesarrolladores buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Notificación con ID " + id + " no encontrada."));
    }

    // Actualizar notificación
    public void actualizarNotificacion(Long id, NotificacionesDesarrolladores nuevosDatos) {
        Optional<NotificacionesDesarrolladores> optional = repository.findById(id);
        if (optional.isPresent()) {
            NotificacionesDesarrolladores existente = optional.get();

            existente.setDescripcion(nuevosDatos.getDescripcion());
            existente.setObservacion(nuevosDatos.getObservacion());
            existente.setLeida(nuevosDatos.isLeida());
            existente.setSolicitudes(nuevosDatos.getSolicitudes());

            repository.save(existente);
        } else {
            throw new EntityNotFoundException("Notificación con ID " + id + " no encontrada.");
        }
    }

    // Eliminar notificación
    public void eliminarPorId(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Notificación con ID " + id + " no encontrada para eliminar.");
        }
    }
}
