package cl.api.buho.proyecto.controllers;

import cl.api.buho.proyecto.models.Solicitudes;
import cl.api.buho.proyecto.services.SolicitudesService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/solicitudes/")
public class SolicitudRestController {

    private final SolicitudesService solicitudesService;

    @Autowired
    public SolicitudRestController(SolicitudesService solicitudesService) {
        this.solicitudesService = solicitudesService;
    }

    @PostMapping(value = "crear", headers = "Accept=application/json")
    public ResponseEntity<String> crearSolicitud(@RequestBody Solicitudes solicitud) {
        try {
            solicitudesService.crearSolicitud(solicitud);
            return ResponseEntity.ok("Solicitud creada correctamente.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping(value = "listar", headers = "Accept=application/json")
    public ResponseEntity<?> listarSolicitudes() {
        try {
            List<Solicitudes> lista = solicitudesService.listarSolicitudes();
            return ResponseEntity.ok(lista);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping(value = "listar/{id}", headers = "Accept=application/json")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            Solicitudes solicitud = solicitudesService.buscarPorId(id);
            return ResponseEntity.ok(solicitud);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping(value = "actualizar/{id}", headers = "Accept=application/json")
    public ResponseEntity<String> actualizarSolicitud(@PathVariable Long id, @RequestBody Solicitudes nuevosDatos) {
        try {
            solicitudesService.actualizarSolicitud(id, nuevosDatos);
            return ResponseEntity.ok("Solicitud actualizada correctamente.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping(value = "eliminar/{id}", headers = "Accept=application/json")
    public ResponseEntity<String> eliminarSolicitud(@PathVariable Long id) {
        try {
            solicitudesService.eliminarSolicitudPorId(id);
            return ResponseEntity.ok("Solicitud eliminada correctamente.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}

