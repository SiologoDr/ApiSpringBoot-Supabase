package cl.api.buho.proyecto.services;

import cl.api.buho.proyecto.models.UsuariosTecnicosSoportes;
import cl.api.buho.proyecto.repositories.IUsuariosTecnicosSoportesRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuariosTecnicosSoportesService {

    private final IUsuariosTecnicosSoportesRepository usuariosTecnicosSoportesRepository;

    @Autowired
    public UsuariosTecnicosSoportesService(IUsuariosTecnicosSoportesRepository usuariosTecnicosSoportesRepository) {
        this.usuariosTecnicosSoportesRepository = usuariosTecnicosSoportesRepository;
    }

    // Crear nueva relación Usuario - Técnico Soporte
    public void crearUsuarioTecnicoSoporte(UsuariosTecnicosSoportes relacion) {
        UsuariosTecnicosSoportes guardado = usuariosTecnicosSoportesRepository.save(relacion);
        if (guardado.getId_usuario_desarrollador() != null) {
            return;
        } else {
            throw new EntityNotFoundException("Error al crear la relación Usuario-Técnico Soporte.");
        }
    }

    // Listar todas las relaciones
    public List<UsuariosTecnicosSoportes> listarUsuariosTecnicosSoportes() {
        List<UsuariosTecnicosSoportes> lista = usuariosTecnicosSoportesRepository.findAll();
        if (lista.isEmpty()) {
            throw new EntityNotFoundException("No hay relaciones Usuario-Técnico Soporte registradas.");
        }
        return lista;
    }

    // Buscar relación por ID
    public UsuariosTecnicosSoportes buscarPorId(Long id) {
        return usuariosTecnicosSoportesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Relación con ID " + id + " no encontrada."));
    }

    // Actualizar relación
    public void actualizarUsuarioTecnicoSoporte(Long id, UsuariosTecnicosSoportes nuevosDatos) {
        Optional<UsuariosTecnicosSoportes> existente = usuariosTecnicosSoportesRepository.findById(id);
        if (existente.isPresent()) {
            UsuariosTecnicosSoportes relacion = existente.get();
            relacion.setUsuarios(nuevosDatos.getUsuarios());
            relacion.setTecnicosSoportes(nuevosDatos.getTecnicosSoportes());
            usuariosTecnicosSoportesRepository.save(relacion);
        } else {
            throw new EntityNotFoundException("Relación con ID " + id + " no encontrada.");
        }
    }

    // Eliminar relación por ID
    public void eliminarUsuarioTecnicoSoportePorId(Long id) {
        if (usuariosTecnicosSoportesRepository.existsById(id)) {
            usuariosTecnicosSoportesRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Relación con ID " + id + " no encontrada para eliminar.");
        }
    }
}
