package cl.api.buho.proyecto.controllers;

import cl.api.buho.proyecto.models.UsuariosTecnicosSoportes;
import cl.api.buho.proyecto.services.UsuariosTecnicosSoportesService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuariostecnicossoportes/")
public class UsuarioTecnicoSoporteRestController {

    private final UsuariosTecnicosSoportesService service;

    @Autowired
    public UsuarioTecnicoSoporteRestController(UsuariosTecnicosSoportesService service) {
        this.service = service;
    }

    @PostMapping(value = "crear", headers = "Accept=application/json")
    public ResponseEntity<String> crear(@RequestBody UsuariosTecnicosSoportes relacion) {
        try {
            service.crearUsuarioTecnicoSoporte(relacion);
            return ResponseEntity.ok("Relación Usuario-Técnico Soporte creada correctamente.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping(value = "listar", headers = "Accept=application/json")
    public ResponseEntity<?> listar() {
        try {
            List<UsuariosTecnicosSoportes> lista = service.listarUsuariosTecnicosSoportes();
            return ResponseEntity.ok(lista);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping(value = "listar/{id}", headers = "Accept=application/json")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            UsuariosTecnicosSoportes relacion = service.buscarPorId(id);
            return ResponseEntity.ok(relacion);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping(value = "actualizar/{id}", headers = "Accept=application/json")
    public ResponseEntity<String> actualizar(@PathVariable Long id, @RequestBody UsuariosTecnicosSoportes nuevosDatos) {
        try {
            service.actualizarUsuarioTecnicoSoporte(id, nuevosDatos);
            return ResponseEntity.ok("Relación actualizada correctamente.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping(value = "eliminar/{id}", headers = "Accept=application/json")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        try {
            service.eliminarUsuarioTecnicoSoportePorId(id);
            return ResponseEntity.ok("Relación eliminada correctamente.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
