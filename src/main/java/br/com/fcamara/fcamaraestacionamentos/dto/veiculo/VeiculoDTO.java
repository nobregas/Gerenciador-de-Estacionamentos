package br.com.fcamara.fcamaraestacionamentos.dto.veiculo;

import br.com.fcamara.fcamaraestacionamentos.entities.enums.Tipo;
import jakarta.validation.constraints.NotNull;

public record VeiculoDTO(

        @NotNull(message = "O campo marca é obrigatório")
        String marca,

        @NotNull(message = "O campo modelo é obrigatório")
        String modelo,

        @NotNull(message = "O campo cor é obrigatório")
        String cor,

        @NotNull(message = "O campo placa é obrigatório")
        String placa,

        @NotNull(message = "O campo tipo é obrigatório")
        Tipo tipo
) {

}
