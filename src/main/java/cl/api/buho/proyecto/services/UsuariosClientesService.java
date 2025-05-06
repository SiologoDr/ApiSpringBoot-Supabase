package cl.api.buho.proyecto.services;

import cl.api.buho.proyecto.models.UsuariosClientes;
import cl.api.buho.proyecto.repositories.IUsuariosClientesRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuariosClientesService {

    private final IUsuariosClientesRepository usuariosClientesRepository;

    @Autowired
    public UsuariosClientesService(IUsuariosClientesRepository usuariosClientesRepository) {
        this.usuariosClientesRepository = usuariosClientesRepository;
    }

    // Metodo para crear una relación entre usuario y cliente
    public void crearUsuarioCliente(UsuariosClientes usuariosClientes) {
        UsuariosClientes usuarioClienteGuardado = usuariosClientesRepository.save(usuariosClientes);
        if (usuarioClienteGuardado.getId_usuario_cliente() != null) {
            return;
        } else {
            throw new EntityNotFoundException("Error al agregar la relación de Usuario y Cliente.");
        }
    }

    // Metodo para listar todas las relaciones de usuarios y clientes
    public List<UsuariosClientes> listarUsuariosClientes() {
        List<UsuariosClientes> usuariosClientes = usuariosClientesRepository.findAll();
        if (usuariosClientes.isEmpty()) {
            throw new EntityNotFoundException("No hay relaciones de Usuario y Cliente registradas.");
        }
        return usuariosClientes;
    }

    // Metodo para buscar una relación por ID
    public UsuariosClientes buscarPorId(Long id) {
        return usuariosClientesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Relación con ID: " + id + " no encontrada."));
    }

    // Metodo para actualizar una relación entre usuario y cliente
    public void actualizarUsuarioCliente(Long id, UsuariosClientes nuevosDatos) {
        Optional<UsuariosClientes> usuarioClienteOptional = usuariosClientesRepository.findById(id);
        if (usuarioClienteOptional.isPresent()) {
            UsuariosClientes usuarioClienteExistente = usuarioClienteOptional.get();

            usuarioClienteExistente.setUsuarios(nuevosDatos.getUsuarios());
            usuarioClienteExistente.setClientes(nuevosDatos.getClientes());

            usuariosClientesRepository.save(usuarioClienteExistente);
        } else {
            throw new EntityNotFoundException("Relación con ID: " + id + " no encontrada.");
        }
    }

    // Metodo para eliminar una relación de usuario y cliente por ID
    public void eliminarUsuarioClientePorId(Long id) {
        if (usuariosClientesRepository.existsById(id)) {
            usuariosClientesRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Relación con ID: " + id + " no encontrada para eliminar.");
        }
    }
}
