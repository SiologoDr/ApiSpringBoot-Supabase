package cl.api.buho.proyecto.services;

import cl.api.buho.proyecto.models.Clientes;
import cl.api.buho.proyecto.repositories.IClientesRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientesService {
    private IClientesRepository clientesRepository;

    @Autowired
    public ClientesService(IClientesRepository clientesRepository) {
        this.clientesRepository = clientesRepository;
    }

    // Metodo para crear un cliente
    public void crearCliente(Clientes cliente) {
        Clientes clienteGuardado = clientesRepository.save(cliente);
        if (clienteGuardado.getId_cliente() != null) {
            return;
        } else {
            throw new EntityNotFoundException("Error al agregar un Cliente.");
        }
    }

    // Metodo para listar todos los clientes
    public List<Clientes> listarClientes() {
        List<Clientes> clientes = clientesRepository.findAll();
        if (clientes.isEmpty()) {
            throw new EntityNotFoundException("No hay Clientes registrados.");
        }
        return clientes;
    }

    // Metodo para buscar un cliente por id
    public Clientes buscarPorId(Long id) {
        return clientesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente con ID: " + id + " no encontrado."));
    }

    // Metodo para actualizar un cliente por id
    public void actualizarCliente(Long id, Clientes nuevosDatos) {
        Optional<Clientes> clienteOptional = clientesRepository.findById(id);
        if (clienteOptional.isPresent()) {
            Clientes clienteExistente = clienteOptional.get();

            clienteExistente.setNombre(nuevosDatos.getNombre());
            clienteExistente.setCorreo(nuevosDatos.getCorreo());
            clienteExistente.setTelefono(nuevosDatos.getTelefono());
            clienteExistente.setEmpresa(nuevosDatos.getEmpresa());

            clientesRepository.save(clienteExistente);
        } else {
            throw new EntityNotFoundException("Cliente con ID " + id + " no encontrado.");
        }
    }

    // Metodo para eliminar un cliente por id
    public void eliminarClientePorId(Long id) {
        if (clientesRepository.existsById(id)) {
            clientesRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Cliente con ID: " + id + " no encontrado para eliminar.");
        }
    }
}
