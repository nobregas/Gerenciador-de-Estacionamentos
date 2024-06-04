package br.com.fcamara.fcamaraestacionamentos.dto.estacionamento;

import br.com.fcamara.fcamaraestacionamentos.entities.Estacionamento;

public record EstacionamentoResponseDTO(
        int vagasDeCarros,

        int vagasDeCarrosOcupadas,

        int vagasDeMotos,

        int vagasDeMotosOcupadas
) {
    public EstacionamentoResponseDTO(Estacionamento estacionamento) {
        this(
                estacionamento.getVagasDeCarros(),
                estacionamento.getVagasDeCarroOcupadas(),
                estacionamento.getVagasDeMotos(),
                estacionamento.getVagasDeMotoOcupadas()
        );
    }
}
