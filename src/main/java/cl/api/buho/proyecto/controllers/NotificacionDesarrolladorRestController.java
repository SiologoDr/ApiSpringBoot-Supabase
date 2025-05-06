package cl.api.buho.proyecto.controllers;

import cl.api.buho.proyecto.models.NotificacionesDesarrolladores;
import cl.api.buho.proyecto.services.NotificacionesDesarrolladoresService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notificacionesdesarrolladores/")
public class NotificacionDesarrolladorRestController {

    private final NotificacionesDesarrolladoresService service;

    @Autowired
    public NotificacionDesarrolladorRestController(NotificacionesDesarrolladoresService service) {
        this.service = service;
    }

    @PostMapping(value = "crear", headers = "Accept=application/json")
    public ResponseEntity<String> crear(@RequestBody NotificacionesDesarrolladores notificacion) {
        try {
            service.crearNotificacion(notificacion);
            return ResponseEntity.ok("Notificación creada correctamente.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping(value = "listar", headers = "Accept=application/json")
    public ResponseEntity<?> listar() {
        try {
            List<NotificacionesDesarrolladores> lista = service.listarNotificaciones();
            return ResponseEntity.ok(lista);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping(value = "listar/{id}", headers = "Accept=application/json")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            NotificacionesDesarrolladores notificacion = service.buscarPorId(id);
            return ResponseEntity.ok(notificacion);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping(value = "actualizar/{id}", headers = "Accept=application/json")
    public ResponseEntity<String> actualizar(@PathVariable Long id, @RequestBody NotificacionesDesarrolladores nuevosDatos) {
        try {
            service.actualizarNotificacion(id, nuevosDatos);
            return ResponseEntity.ok("Notificación actualizada correctamente.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping(value = "eliminar/{id}", headers = "Accept=application/json")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        try {
            service.eliminarPorId(id);
            return ResponseEntity.ok("Notificación eliminada correctamente.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}

