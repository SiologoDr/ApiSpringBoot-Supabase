package cl.api.buho.proyecto.controllers;

import cl.api.buho.proyecto.models.UsuariosDesarrolladores;
import cl.api.buho.proyecto.services.UsuariosDesarrolladoresService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuariosdesarrolladores/")
public class UsuarioDesarrolladorRestController {

    private final UsuariosDesarrolladoresService usuariosDesarrolladoresService;

    @Autowired
    public UsuarioDesarrolladorRestController(UsuariosDesarrolladoresService usuariosDesarrolladoresService) {
        this.usuariosDesarrolladoresService = usuariosDesarrolladoresService;
    }

    // Endpoint para crear una relación entre usuario y desarrollador
    @PostMapping(value = "crear", headers = "Accept=application/json")
    public ResponseEntity<String> crearUsuarioDesarrollador(@RequestBody UsuariosDesarrolladores usuariosDesarrolladores) {
        try {
            usuariosDesarrolladoresService.crearUsuarioDesarrollador(usuariosDesarrolladores);
            return ResponseEntity.ok("Relación Usuario-Desarrollador creada correctamente.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // Endpoint para listar todas las relaciones de usuario y desarrollador
    @GetMapping(value = "listar", headers = "Accept=application/json")
    public ResponseEntity<?> listarUsuariosDesarrolladores() {
        try {
            List<UsuariosDesarrolladores> usuariosDesarrolladores = usuariosDesarrolladoresService.listarUsuariosDesarrolladores();
            return ResponseEntity.ok(usuariosDesarrolladores);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // Endpoint para buscar una relación por ID
    @GetMapping(value = "listar/{id}", headers = "Accept=application/json")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            UsuariosDesarrolladores usuariosDesarrolladores = usuariosDesarrolladoresService.buscarPorId(id);
            return ResponseEntity.ok(usuariosDesarrolladores);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // Endpoint para actualizar una relación de usuario y desarrollador
    @PutMapping(value = "actualizar/{id}", headers = "Accept=application/json")
    public ResponseEntity<String> actualizarUsuarioDesarrollador(@PathVariable Long id, @RequestBody UsuariosDesarrolladores nuevosDatos) {
        try {
            usuariosDesarrolladoresService.actualizarUsuarioDesarrollador(id, nuevosDatos);
            return ResponseEntity.ok("Relación Usuario-Desarrollador actualizada correctamente.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // Endpoint para eliminar una relación de usuario y desarrollador por ID
    @DeleteMapping(value = "eliminar/{id}", headers = "Accept=application/json")
    public ResponseEntity<String> eliminarUsuarioDesarrollador(@PathVariable Long id) {
        try {
            usuariosDesarrolladoresService.eliminarUsuarioDesarrolladorPorId(id);
            return ResponseEntity.ok("Relación Usuario-Desarrollador eliminada correctamente.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
