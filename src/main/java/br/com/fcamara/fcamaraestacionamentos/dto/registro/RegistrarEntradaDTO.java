package br.com.fcamara.fcamaraestacionamentos.dto.registro;

import jakarta.validation.constraints.NotNull;

public record RegistrarEntradaDTO(
        @NotNull
        String estacionamentoId,
        @NotNull
        String veiculoId
) {
}
