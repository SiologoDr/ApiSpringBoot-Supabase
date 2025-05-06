package cl.api.buho.proyecto.services;

import cl.api.buho.proyecto.models.TecnicosSoportes;
import cl.api.buho.proyecto.repositories.ITecnicosSoportesRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicosSoportesService {
    private ITecnicosSoportesRepository tecnicosSoportesRepository;

    @Autowired
    public TecnicosSoportesService(ITecnicosSoportesRepository tecnicosSoportesRepository) {
        this.tecnicosSoportesRepository = tecnicosSoportesRepository;
    }

    // Metodo para crear técnico de soporte
    public void crearTecnico(TecnicosSoportes tecnico) {
        TecnicosSoportes guardado = tecnicosSoportesRepository.save(tecnico);
        if (guardado.getId_tecnico_soporte() != null) {
            return;
        } else {
            throw new EntityNotFoundException("Error al agregar un Técnico de Soporte.");
        }
    }

    // Metodo para listar todos los técnicos de soporte
    public List<TecnicosSoportes> listarTecnicos() {
        List<TecnicosSoportes> tecnicos = tecnicosSoportesRepository.findAll();
        if (tecnicos.isEmpty()) {
            throw new EntityNotFoundException("No hay Técnicos de Soporte registrados.");
        }
        return tecnicos;
    }

    // Metodo para buscar un técnico por ID
    public TecnicosSoportes buscarPorId(Long id) {
        return tecnicosSoportesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Técnico de Soporte con ID: " + id + " no encontrado."));
    }

    // Metodo para actualizar un técnico por ID
    public void actualizarTecnico(Long id, TecnicosSoportes nuevosDatos) {
        Optional<TecnicosSoportes> tecnicoOptional = tecnicosSoportesRepository.findById(id);
        if (tecnicoOptional.isPresent()) {
            TecnicosSoportes tecnicoExistente = tecnicoOptional.get();

            tecnicoExistente.setNombre(nuevosDatos.getNombre());
            tecnicoExistente.setCorreo(nuevosDatos.getCorreo());
            tecnicoExistente.setTelefono(nuevosDatos.getTelefono());
            tecnicoExistente.setEspecialidad(nuevosDatos.getEspecialidad());

            tecnicosSoportesRepository.save(tecnicoExistente);
        } else {
            throw new EntityNotFoundException("Técnico con ID " + id + " no encontrado.");
        }
    }

    // Metodo para eliminar un técnico por ID
    public void eliminarTecnicoPorId(Long id) {
        if (tecnicosSoportesRepository.existsById(id)) {
            tecnicosSoportesRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Técnico con ID: " + id + " no encontrado para eliminar.");
        }
    }
}
