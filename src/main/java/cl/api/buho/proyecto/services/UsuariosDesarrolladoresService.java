package cl.api.buho.proyecto.services;

import cl.api.buho.proyecto.models.UsuariosDesarrolladores;
import cl.api.buho.proyecto.repositories.IUsuariosDesarrolladoresRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuariosDesarrolladoresService {

    private final IUsuariosDesarrolladoresRepository usuariosDesarrolladoresRepository;

    @Autowired
    public UsuariosDesarrolladoresService(IUsuariosDesarrolladoresRepository usuariosDesarrolladoresRepository) {
        this.usuariosDesarrolladoresRepository = usuariosDesarrolladoresRepository;
    }

    // Metodo para crear una relación entre usuario y desarrollador
    public void crearUsuarioDesarrollador(UsuariosDesarrolladores usuariosDesarrolladores) {
        UsuariosDesarrolladores usuarioDesarrolladorGuardado = usuariosDesarrolladoresRepository.save(usuariosDesarrolladores);
        if (usuarioDesarrolladorGuardado.getId_usuario_desarrollador() != null) {
            return;
        } else {
            throw new EntityNotFoundException("Error al agregar la relación de Usuario y Desarrollador.");
        }
    }

    // Metodo para listar todas las relaciones de usuario y desarrollador
    public List<UsuariosDesarrolladores> listarUsuariosDesarrolladores() {
        List<UsuariosDesarrolladores> usuariosDesarrolladores = usuariosDesarrolladoresRepository.findAll();
        if (usuariosDesarrolladores.isEmpty()) {
            throw new EntityNotFoundException("No hay relaciones de Usuario y Desarrollador registradas.");
        }
        return usuariosDesarrolladores;
    }

    // Metodo para buscar una relación por ID
    public UsuariosDesarrolladores buscarPorId(Long id) {
        return usuariosDesarrolladoresRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Relación con ID: " + id + " no encontrada."));
    }

    // Metodo para actualizar una relación entre usuario y desarrollador
    public void actualizarUsuarioDesarrollador(Long id, UsuariosDesarrolladores nuevosDatos) {
        Optional<UsuariosDesarrolladores> usuarioDesarrolladorOptional = usuariosDesarrolladoresRepository.findById(id);
        if (usuarioDesarrolladorOptional.isPresent()) {
            UsuariosDesarrolladores usuarioDesarrolladorExistente = usuarioDesarrolladorOptional.get();

            // Actualizando los datos si es necesario
            usuarioDesarrolladorExistente.setUsuarios(nuevosDatos.getUsuarios());
            usuarioDesarrolladorExistente.setDesarrolladores(nuevosDatos.getDesarrolladores());

            usuariosDesarrolladoresRepository.save(usuarioDesarrolladorExistente);
        } else {
            throw new EntityNotFoundException("Relación con ID: " + id + " no encontrada.");
        }
    }

    // Metodo para eliminar una relación de usuario y desarrollador por ID
    public void eliminarUsuarioDesarrolladorPorId(Long id) {
        if (usuariosDesarrolladoresRepository.existsById(id)) {
            usuariosDesarrolladoresRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Relación con ID: " + id + " no encontrada para eliminar.");
        }
    }
}
