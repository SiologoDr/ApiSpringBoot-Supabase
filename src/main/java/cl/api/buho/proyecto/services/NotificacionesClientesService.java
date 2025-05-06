package cl.api.buho.proyecto.services;

import cl.api.buho.proyecto.models.NotificacionesClientes;
import cl.api.buho.proyecto.repositories.INotificacionesClientesRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificacionesClientesService {

    private final INotificacionesClientesRepository notificacionesClientesRepository;

    @Autowired
    public NotificacionesClientesService(INotificacionesClientesRepository notificacionesClientesRepository) {
        this.notificacionesClientesRepository = notificacionesClientesRepository;
    }

    // Crear notificación
    public void crearNotificacion(NotificacionesClientes notificacion) {
        NotificacionesClientes guardada = notificacionesClientesRepository.save(notificacion);
        if (guardada.getId_notificacion() == null) {
            throw new EntityNotFoundException("Error al guardar la notificación del cliente.");
        }
    }

    // Listar todas las notificaciones
    public List<NotificacionesClientes> listarNotificaciones() {
        List<NotificacionesClientes> notificaciones = notificacionesClientesRepository.findAll();
        if (notificaciones.isEmpty()) {
            throw new EntityNotFoundException("No hay notificaciones de clientes registradas.");
        }
        return notificaciones;
    }

    // Buscar por ID
    public NotificacionesClientes buscarPorId(Long id) {
        return notificacionesClientesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Notificación con ID " + id + " no encontrada."));
    }

    // Eliminar por ID
    public void eliminarPorId(Long id) {
        if (notificacionesClientesRepository.existsById(id)) {
            notificacionesClientesRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("No se encontró la notificación para eliminar.");
        }
    }
}
