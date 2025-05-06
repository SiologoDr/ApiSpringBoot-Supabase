package cl.api.buho.proyecto.controllers;

import cl.api.buho.proyecto.models.Clientes;
import cl.api.buho.proyecto.services.ClientesService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes/")
public class ClienteRestController {
    private ClientesService clientesService;

    @Autowired
    public ClienteRestController(ClientesService clientesService) {
        this.clientesService = clientesService;
    }

    @PostMapping(value = "crear", headers = "Accept=application/json")
    public ResponseEntity<String> crearCliente(@RequestBody Clientes cliente){
        try {
            clientesService.crearCliente(cliente);
            return ResponseEntity.ok("Cliente creado correctamente.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping(value = "listar", headers = "Accept=application/json")
    public ResponseEntity<?> listarClientes(){
        try {
            List<Clientes> clientes = clientesService.listarClientes();
            return ResponseEntity.ok(clientes);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping(value = "listar/{id}", headers = "Accept=application/json")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            Clientes cliente = clientesService.buscarPorId(id);
            return ResponseEntity.ok(cliente);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping(value = "actualizar/{id}", headers = "Accept=application/json")
    public ResponseEntity<String> actualizarPorId(@PathVariable Long id, @RequestBody Clientes nuevosDatos) {
        try {
            clientesService.actualizarCliente(id, nuevosDatos);
            return ResponseEntity.ok("Cliente actualizado correctamente.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping(value = "eliminar/{id}", headers = "Accept=application/json")
    public ResponseEntity<String> eliminarCliente(@PathVariable Long id) {
        try {
            clientesService.eliminarClientePorId(id);
            return ResponseEntity.ok("Cliente eliminado correctamente.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
