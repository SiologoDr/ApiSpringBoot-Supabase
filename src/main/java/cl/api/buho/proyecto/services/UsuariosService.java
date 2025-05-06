package cl.api.buho.proyecto.services;

import cl.api.buho.proyecto.models.Usuarios;
import cl.api.buho.proyecto.repositories.IUsuariosRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuariosService {
    private IUsuariosRepository usuariosRepository;

    @Autowired
    public UsuariosService(IUsuariosRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
    }

    //Metodo para crear usuarios
    public void crearUsuario(Usuarios usuarios){
        Usuarios usuarioGuardado = usuariosRepository.save(usuarios);
        if (usuarioGuardado.getId_usuario() != null) {
            return;
        } else {
            throw new EntityNotFoundException("Error al Agregar un Usuario.");
        }
    }

    //Metodo para listar todos los usuarios
    public List<Usuarios> listarUsuarios(){
        List<Usuarios> usuarios = usuariosRepository.findAll();
        if (usuarios.isEmpty()) {
            throw new EntityNotFoundException("No hay Usuarios registrados.");
        }
        return usuarios;
    }

    //Metodo para buscar un usuario por id
    public Usuarios buscarPorId(Long id){
        return usuariosRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario con ID: " + id + " ,no encontrado."));
    }

    //Metodo para actualizar un usuario por id
    public void actualizarUsuario(Long id, Usuarios nuevosDatos) {
        Optional<Usuarios> usuarioOptional = usuariosRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            Usuarios usuarioExistente = usuarioOptional.get();

            usuarioExistente.setNombre(nuevosDatos.getNombre());
            usuarioExistente.setCorreo(nuevosDatos.getCorreo());
            usuarioExistente.setTelefono(nuevosDatos.getTelefono());

            usuariosRepository.save(usuarioExistente);
        } else {
            throw new EntityNotFoundException("Usuario con ID " + id + " no encontrado.");
        }
    }

    //Metodo para eliminar un usuario
    public void eliminarUsuarioPorId(Long id){
        if (usuariosRepository.existsById(id)) {
            usuariosRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Usuario con ID: " + id + " ,no encontrado para eliminar.");
        }
    }
}
