package cl.app.taskflow.controller;

import cl.app.taskflow.model.dto.TareaRequestDTO;
import cl.app.taskflow.model.dto.TareaResponseDTO;
import cl.app.taskflow.service.TareaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/tareas")
public class TareaController {

    private final TareaService service;

    public TareaController(TareaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TareaResponseDTO> crear(@Valid @RequestBody TareaRequestDTO tareaDTO) {
        TareaResponseDTO nuevaTarea = service.crear(tareaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaTarea);
    }

    @GetMapping
    public ResponseEntity<List<TareaResponseDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TareaResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TareaResponseDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody TareaRequestDTO tareaDTO
    ) {
        TareaResponseDTO tareaActualizada = service.actualizar(id, tareaDTO);
        return ResponseEntity.ok(tareaActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> eliminar(@PathVariable Long id) {
        service.eliminar(id);

        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Tarea eliminada correctamente");

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<TareaResponseDTO>> buscarPorEstado(@PathVariable String estado) {
        return ResponseEntity.ok(service.buscarPorEstado(estado));
    }
}