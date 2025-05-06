package cl.api.buho.proyecto.services;

import cl.api.buho.proyecto.models.Solicitudes;
import cl.api.buho.proyecto.repositories.ISolicitudesRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SolicitudesService {

    private final ISolicitudesRepository solicitudesRepository;

    @Autowired
    public SolicitudesService(ISolicitudesRepository solicitudesRepository) {
        this.solicitudesRepository = solicitudesRepository;
    }

    // Crear una nueva solicitud
    public void crearSolicitud(Solicitudes solicitud) {
        Solicitudes guardada = solicitudesRepository.save(solicitud);
        if (guardada.getId_solicitud() == null) {
            throw new EntityNotFoundException("Error al crear la solicitud.");
        }
    }

    // Listar todas las solicitudes
    public List<Solicitudes> listarSolicitudes() {
        List<Solicitudes> lista = solicitudesRepository.findAll();
        if (lista.isEmpty()) {
            throw new EntityNotFoundException("No hay solicitudes registradas.");
        }
        return lista;
    }

    // Buscar solicitud por ID
    public Solicitudes buscarPorId(Long id) {
        return solicitudesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Solicitud con ID " + id + " no encontrada."));
    }

    // Actualizar solicitud
    public void actualizarSolicitud(Long id, Solicitudes nuevosDatos) {
        Optional<Solicitudes> solicitudOptional = solicitudesRepository.findById(id);
        if (solicitudOptional.isPresent()) {
            Solicitudes solicitudExistente = solicitudOptional.get();

            solicitudExistente.setClientes(nuevosDatos.getClientes());
            solicitudExistente.setDescripcion(nuevosDatos.getDescripcion());
            solicitudExistente.setObservacion_tecnico_soporte(nuevosDatos.getObservacion_tecnico_soporte());
            solicitudExistente.setObservacion_desarrollador(nuevosDatos.getObservacion_desarrollador());
            solicitudExistente.setEstados(nuevosDatos.getEstados());
            solicitudExistente.setEstadosRevisiones(nuevosDatos.getEstadosRevisiones());
            solicitudExistente.setTecnicosSoportes(nuevosDatos.getTecnicosSoportes());
            solicitudExistente.setDesarrolladores(nuevosDatos.getDesarrolladores());

            solicitudesRepository.save(solicitudExistente);
        } else {
            throw new EntityNotFoundException("Solicitud con ID " + id + " no encontrada.");
        }
    }

    // Eliminar solicitud
    public void eliminarSolicitudPorId(Long id) {
        if (solicitudesRepository.existsById(id)) {
            solicitudesRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Solicitud con ID " + id + " no encontrada para eliminar.");
        }
    }
}
