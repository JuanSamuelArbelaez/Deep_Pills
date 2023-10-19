package deep_pills.dto.authentications;
public record MensajeDTO<T>(
        boolean error,
        T respuesta) {
}