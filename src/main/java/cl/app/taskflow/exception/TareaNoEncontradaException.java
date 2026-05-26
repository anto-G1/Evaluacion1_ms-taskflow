package cl.app.taskflow.exception;

public class TareaNoEncontradaException extends RuntimeException {

    public TareaNoEncontradaException(String mensaje) {
        super(mensaje);
    }
}