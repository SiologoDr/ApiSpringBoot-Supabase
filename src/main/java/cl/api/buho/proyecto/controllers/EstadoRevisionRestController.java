package cl.api.buho.proyecto.controllers;

import cl.api.buho.proyecto.models.EstadosRevisiones;
import cl.api.buho.proyecto.services.EstadosRevisionesService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estadosrevisiones/")
public class EstadoRevisionRestController {

    private final EstadosRevisionesService estadosRevisionesService;

    @Autowired
    public EstadoRevisionRestController(EstadosRevisionesService estadosRevisionesService) {
        this.estadosRevisionesService = estadosRevisionesService;
    }

    @PostMapping(value = "crear", headers = "Accept=application/json")
    public ResponseEntity<String> crearEstadoRevision(@RequestBody EstadosRevisiones estado) {
        try {
            estadosRevisionesService.crearEstadoRevision(estado);
            return ResponseEntity.ok("Estado de Revisión creado correctamente.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping(value = "listar", headers = "Accept=application/json")
    public ResponseEntity<?> listarEstadosRevisiones() {
        try {
            List<EstadosRevisiones> lista = estadosRevisionesService.listarEstadosRevisiones();
            return ResponseEntity.ok(lista);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping(value = "listar/{id}", headers = "Accept=application/json")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            EstadosRevisiones estado = estadosRevisionesService.buscarPorId(id);
            return ResponseEntity.ok(estado);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping(value = "actualizar/{id}", headers = "Accept=application/json")
    public ResponseEntity<String> actualizarEstadoRevision(@PathVariable Long id, @RequestBody EstadosRevisiones nuevosDatos) {
        try {
            estadosRevisionesService.actualizarEstadoRevision(id, nuevosDatos);
            return ResponseEntity.ok("Estado de Revisión actualizado correctamente.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping(value = "eliminar/{id}", headers = "Accept=application/json")
    public ResponseEntity<String> eliminarEstadoRevision(@PathVariable Long id) {
        try {
            estadosRevisionesService.eliminarEstadoRevision(id);
            return ResponseEntity.ok("Estado de Revisión eliminado correctamente.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
