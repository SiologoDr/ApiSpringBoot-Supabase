package cl.api.buho.proyecto.controllers;

import cl.api.buho.proyecto.models.UsuariosClientes;
import cl.api.buho.proyecto.services.UsuariosClientesService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuariosclientes/")
public class UsuarioClienteRestController {

    private final UsuariosClientesService usuariosClientesService;

    @Autowired
    public UsuarioClienteRestController(UsuariosClientesService usuariosClientesService) {
        this.usuariosClientesService = usuariosClientesService;
    }

    // Endpoint para crear una relación entre usuario y cliente
    @PostMapping(value = "crear", headers = "Accept=application/json")
    public ResponseEntity<String> crearUsuarioCliente(@RequestBody UsuariosClientes usuariosClientes) {
        try {
            usuariosClientesService.crearUsuarioCliente(usuariosClientes);
            return ResponseEntity.ok("Relación Usuario-Cliente creada correctamente.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // Endpoint para listar todas las relaciones de usuario y cliente
    @GetMapping(value = "listar", headers = "Accept=application/json")
    public ResponseEntity<?> listarUsuariosClientes() {
        try {
            List<UsuariosClientes> usuariosClientes = usuariosClientesService.listarUsuariosClientes();
            return ResponseEntity.ok(usuariosClientes);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // Endpoint para buscar una relación por ID
    @GetMapping(value = "listar/{id}", headers = "Accept=application/json")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            UsuariosClientes usuariosClientes = usuariosClientesService.buscarPorId(id);
            return ResponseEntity.ok(usuariosClientes);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // Endpoint para actualizar una relación de usuario y cliente
    @PutMapping(value = "actualizar/{id}", headers = "Accept=application/json")
    public ResponseEntity<String> actualizarUsuarioCliente(@PathVariable Long id, @RequestBody UsuariosClientes nuevosDatos) {
        try {
            usuariosClientesService.actualizarUsuarioCliente(id, nuevosDatos);
            return ResponseEntity.ok("Relación Usuario-Cliente actualizada correctamente.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // Endpoint para eliminar una relación de usuario y cliente por ID
    @DeleteMapping(value = "eliminar/{id}", headers = "Accept=application/json")
    public ResponseEntity<String> eliminarUsuarioCliente(@PathVariable Long id) {
        try {
            usuariosClientesService.eliminarUsuarioClientePorId(id);
            return ResponseEntity.ok("Relación Usuario-Cliente eliminada correctamente.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}

