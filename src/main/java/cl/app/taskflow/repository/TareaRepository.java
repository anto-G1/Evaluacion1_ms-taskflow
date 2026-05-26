package cl.app.taskflow.repository;

import cl.app.taskflow.model.Tarea;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TareaRepository {

    private final Map<Long, Tarea> tareas = new HashMap<>();
    private Long contadorId = 1L;

    public Tarea guardar(Tarea tarea) {
        tarea.setId(contadorId);
        tareas.put(contadorId, tarea);
        contadorId++;
        return tarea;
    }

    public List<Tarea> listar() {
        return new ArrayList<>(tareas.values());
    }

    public Tarea buscarPorId(Long id) {
        return tareas.get(id);
    }

    public Tarea actualizar(Long id, Tarea tarea) {
        tarea.setId(id);
        tareas.put(id, tarea);
        return tarea;
    }

    public void eliminar(Long id) {
        tareas.remove(id);
    }

    public boolean existe(Long id) {
        return tareas.containsKey(id);
    }
}