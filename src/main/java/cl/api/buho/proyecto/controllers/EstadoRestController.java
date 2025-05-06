package cl.api.buho.proyecto.controllers;

import cl.api.buho.proyecto.models.Estados;
import cl.api.buho.proyecto.services.EstadosService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estados/")
public class EstadoRestController {

    private final EstadosService estadosService;

    @Autowired
    public EstadoRestController(EstadosService estadosService) {
        this.estadosService = estadosService;
    }

    @PostMapping(value = "crear", headers = "Accept=application/json")
    public ResponseEntity<String> crearEstado(@RequestBody Estados estado) {
        try {
            estadosService.crearEstado(estado);
            return ResponseEntity.ok("Estado creado correctamente.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping(value = "listar", headers = "Accept=application/json")
    public ResponseEntity<?> listarEstados() {
        try {
            List<Estados> lista = estadosService.listarEstados();
            return ResponseEntity.ok(lista);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping(value = "listar/{id}", headers = "Accept=application/json")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            Estados estado = estadosService.buscarPorId(id);
            return ResponseEntity.ok(estado);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping(value = "actualizar/{id}", headers = "Accept=application/json")
    public ResponseEntity<String> actualizarEstado(@PathVariable Long id, @RequestBody Estados nuevosDatos) {
        try {
            estadosService.actualizarEstado(id, nuevosDatos);
            return ResponseEntity.ok("Estado actualizado correctamente.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping(value = "eliminar/{id}", headers = "Accept=application/json")
    public ResponseEntity<String> eliminarEstado(@PathVariable Long id) {
        try {
            estadosService.eliminarEstado(id);
            return ResponseEntity.ok("Estado eliminado correctamente.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
