package cl.api.buho.proyecto.controllers;

import cl.api.buho.proyecto.models.NotificacionesClientes;
import cl.api.buho.proyecto.services.NotificacionesClientesService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notificacionesclientes/")
public class NotificacionClienteRestController {

    private final NotificacionesClientesService notificacionesClientesService;

    @Autowired
    public NotificacionClienteRestController(NotificacionesClientesService notificacionesClientesService) {
        this.notificacionesClientesService = notificacionesClientesService;
    }

    @PostMapping(value = "crear", headers = "Accept=application/json")
    public ResponseEntity<String> crear(@RequestBody NotificacionesClientes notificacion) {
        try {
            notificacionesClientesService.crearNotificacion(notificacion);
            return ResponseEntity.ok("Notificación de cliente creada correctamente.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping(value = "listar", headers = "Accept=application/json")
    public ResponseEntity<?> listar() {
        try {
            List<NotificacionesClientes> lista = notificacionesClientesService.listarNotificaciones();
            return ResponseEntity.ok(lista);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping(value = "listar/{id}", headers = "Accept=application/json")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            NotificacionesClientes notificacion = notificacionesClientesService.buscarPorId(id);
            return ResponseEntity.ok(notificacion);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping(value = "eliminar/{id}", headers = "Accept=application/json")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        try {
            notificacionesClientesService.eliminarPorId(id);
            return ResponseEntity.ok("Notificación eliminada correctamente.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
