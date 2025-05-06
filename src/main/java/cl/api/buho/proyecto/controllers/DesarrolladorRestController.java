package cl.api.buho.proyecto.controllers;

import cl.api.buho.proyecto.models.Desarrolladores;
import cl.api.buho.proyecto.services.DesarrolladoresService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/desarrolladores/")
public class DesarrolladorRestController {

    private final DesarrolladoresService desarrolladoresService;

    @Autowired
    public DesarrolladorRestController(DesarrolladoresService desarrolladoresService) {
        this.desarrolladoresService = desarrolladoresService;
    }

    @PostMapping(value = "crear", headers = "Accept=application/json")
    public ResponseEntity<String> crearDesarrollador(@RequestBody Desarrolladores desarrollador) {
        try {
            desarrolladoresService.crearDesarrollador(desarrollador);
            return ResponseEntity.ok("Desarrollador creado correctamente.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping(value = "listar", headers = "Accept=application/json")
    public ResponseEntity<?> listarDesarrolladores() {
        try {
            List<Desarrolladores> lista = desarrolladoresService.listarDesarrolladores();
            return ResponseEntity.ok(lista);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping(value = "listar/{id}", headers = "Accept=application/json")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            Desarrolladores desarrollador = desarrolladoresService.buscarPorId(id);
            return ResponseEntity.ok(desarrollador);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping(value = "actualizar/{id}", headers = "Accept=application/json")
    public ResponseEntity<String> actualizarDesarrollador(@PathVariable Long id, @RequestBody Desarrolladores nuevosDatos) {
        try {
            desarrolladoresService.actualizarDesarrollador(id, nuevosDatos);
            return ResponseEntity.ok("Desarrollador actualizado correctamente.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping(value = "eliminar/{id}", headers = "Accept=application/json")
    public ResponseEntity<String> eliminarDesarrollador(@PathVariable Long id) {
        try {
            desarrolladoresService.eliminarDesarrollador(id);
            return ResponseEntity.ok("Desarrollador eliminado correctamente.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
