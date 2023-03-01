package sergio.pruebas.gym.management.entities.dtos;

import java.time.OffsetDateTime;

public record ErrorDto(String message, OffsetDateTime executionTime) {
}
