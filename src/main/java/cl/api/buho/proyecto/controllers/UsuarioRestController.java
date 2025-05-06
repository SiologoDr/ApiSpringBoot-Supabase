package cl.api.buho.proyecto.controllers;

import cl.api.buho.proyecto.models.Usuarios;
import cl.api.buho.proyecto.services.UsuariosService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios/")
public class UsuarioRestController {
    private UsuariosService usuariosService;

    @Autowired
    public UsuarioRestController(UsuariosService usuariosService) {
        this.usuariosService = usuariosService;
    }

    @PostMapping(value = "crear", headers = "Accept=application/json")
    public ResponseEntity<String> crearUsuario(@RequestBody Usuarios usuarios){
        try {
            usuariosService.crearUsuario(usuarios);
            return ResponseEntity.ok("Usuario creado correctamente.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping(value = "listar", headers = "Accept=application/json")
    public ResponseEntity<?> listarUsuario(){
        try {
            List<Usuarios> usuarios = usuariosService.listarUsuarios();
            return ResponseEntity.ok(usuarios);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping(value = "listar/{id}", headers = "Accept=application/json")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            Usuarios usuario = usuariosService.buscarPorId(id);
            return ResponseEntity.ok(usuario);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping(value = "actualizar/{id}", headers = "Accept=application/json")
    public ResponseEntity<String> actualizarPorId(@PathVariable Long id, @RequestBody Usuarios nuevosDatos) {
        try {
            usuariosService.actualizarUsuario(id, nuevosDatos);
            return ResponseEntity.ok("Usuario actualizado correctamente.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping(value = "eliminar/{id}", headers = "Accept=application/json")
    public ResponseEntity<String> eliminarUsuario(@PathVariable Long id) {
        try {
            usuariosService.eliminarUsuarioPorId(id);
            return ResponseEntity.ok("Usuario eliminado correctamente.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
