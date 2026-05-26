package cl.app.taskflow.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tarea {

    private Long id;
    private String titulo;
    private String descripcion;
    private String estado;
    private String prioridad;
    private String responsable;
    private LocalDate fechaCreacion;
    private LocalDate fechaLimite;
}