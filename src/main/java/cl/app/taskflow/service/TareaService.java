package cl.app.taskflow.service;

import cl.app.taskflow.exception.TareaNoEncontradaException;
import cl.app.taskflow.model.Tarea;
import cl.app.taskflow.model.dto.TareaRequestDTO;
import cl.app.taskflow.model.dto.TareaResponseDTO;
import cl.app.taskflow.repository.TareaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TareaService {

    private final TareaRepository repository;

    public TareaService(TareaRepository repository) {
        this.repository = repository;
    }

    public TareaResponseDTO crear(TareaRequestDTO dto) {
        Tarea tarea = new Tarea();

        tarea.setTitulo(dto.getTitulo());
        tarea.setDescripcion(dto.getDescripcion());
        tarea.setEstado(dto.getEstado());
        tarea.setPrioridad(dto.getPrioridad());
        tarea.setResponsable(dto.getResponsable());
        tarea.setFechaCreacion(LocalDate.now());
        tarea.setFechaLimite(dto.getFechaLimite());

        Tarea tareaGuardada = repository.guardar(tarea);

        return convertirAResponseDTO(tareaGuardada);
    }

    public List<TareaResponseDTO> listar() {
        return repository.listar()
                .stream()
                .map(this::convertirAResponseDTO)
                .toList();
    }

    public TareaResponseDTO buscarPorId(Long id) {
        Tarea tarea = repository.buscarPorId(id);

        if (tarea == null) {
            throw new TareaNoEncontradaException("No existe una tarea con el ID: " + id);
        }

        return convertirAResponseDTO(tarea);
    }

    public TareaResponseDTO actualizar(Long id, TareaRequestDTO dto) {
        if (!repository.existe(id)) {
            throw new TareaNoEncontradaException("No se puede actualizar. No existe una tarea con el ID: " + id);
        }

        Tarea tareaExistente = repository.buscarPorId(id);

        tareaExistente.setTitulo(dto.getTitulo());
        tareaExistente.setDescripcion(dto.getDescripcion());
        tareaExistente.setEstado(dto.getEstado());
        tareaExistente.setPrioridad(dto.getPrioridad());
        tareaExistente.setResponsable(dto.getResponsable());
        tareaExistente.setFechaLimite(dto.getFechaLimite());

        Tarea tareaActualizada = repository.actualizar(id, tareaExistente);

        return convertirAResponseDTO(tareaActualizada);
    }

    public void eliminar(Long id) {
        if (!repository.existe(id)) {
            throw new TareaNoEncontradaException("No se puede eliminar. No existe una tarea con el ID: " + id);
        }

        repository.eliminar(id);
    }

    public List<TareaResponseDTO> buscarPorEstado(String estado) {
        return repository.listar()
                .stream()
                .filter(tarea -> tarea.getEstado().equalsIgnoreCase(estado))
                .map(this::convertirAResponseDTO)
                .toList();
    }

    private TareaResponseDTO convertirAResponseDTO(Tarea tarea) {
        return new TareaResponseDTO(
                tarea.getId(),
                tarea.getTitulo(),
                tarea.getDescripcion(),
                tarea.getEstado(),
                tarea.getPrioridad(),
                tarea.getResponsable(),
                tarea.getFechaCreacion(),
                tarea.getFechaLimite()
        );
    }
}