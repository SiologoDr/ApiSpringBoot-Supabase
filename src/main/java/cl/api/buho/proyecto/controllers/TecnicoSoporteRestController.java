package cl.api.buho.proyecto.controllers;

import cl.api.buho.proyecto.models.TecnicosSoportes;
import cl.api.buho.proyecto.services.TecnicosSoportesService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tecnicossoportes/")
public class TecnicoSoporteRestController {
    private TecnicosSoportesService tecnicosSoportesService;

    @Autowired
    public TecnicoSoporteRestController(TecnicosSoportesService tecnicosSoportesService) {
        this.tecnicosSoportesService = tecnicosSoportesService;
    }

    @PostMapping(value = "crear", headers = "Accept=application/json")
    public ResponseEntity<String> crearTecnico(@RequestBody TecnicosSoportes tecnico) {
        try {
            tecnicosSoportesService.crearTecnico(tecnico);
            return ResponseEntity.ok("Técnico de Soporte creado correctamente.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping(value = "listar", headers = "Accept=application/json")
    public ResponseEntity<?> listarTecnicos() {
        try {
            List<TecnicosSoportes> tecnicos = tecnicosSoportesService.listarTecnicos();
            return ResponseEntity.ok(tecnicos);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping(value = "listar/{id}", headers = "Accept=application/json")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            TecnicosSoportes tecnico = tecnicosSoportesService.buscarPorId(id);
            return ResponseEntity.ok(tecnico);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping(value = "actualizar/{id}", headers = "Accept=application/json")
    public ResponseEntity<String> actualizarTecnico(@PathVariable Long id, @RequestBody TecnicosSoportes nuevosDatos) {
        try {
            tecnicosSoportesService.actualizarTecnico(id, nuevosDatos);
            return ResponseEntity.ok("Técnico de Soporte actualizado correctamente.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping(value = "eliminar/{id}", headers = "Accept=application/json")
    public ResponseEntity<String> eliminarTecnico(@PathVariable Long id) {
        try {
            tecnicosSoportesService.eliminarTecnicoPorId(id);
            return ResponseEntity.ok("Técnico de Soporte eliminado correctamente.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
