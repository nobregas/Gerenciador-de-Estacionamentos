package br.com.fcamara.fcamaraestacionamentos.dto.registro;

import br.com.fcamara.fcamaraestacionamentos.entities.Estabelecimento;
import br.com.fcamara.fcamaraestacionamentos.entities.Registro;


public record RegistroResponse(
        String estabelecimentoId,

        Registro registro,

        int vagasDeCarros,

        int vagasDeCarroOcupadas,

        int vagasDeMotos,

        int vagasDeMotoOcupadas


) {
    public RegistroResponse(Estabelecimento estabelecimento, Registro registro) {
        this(
                estabelecimento.getId(),
                registro,
                estabelecimento.getEstacionamento().getVagasDeCarros(),
                estabelecimento.getEstacionamento().getVagasDeCarroOcupadas(),
                estabelecimento.getEstacionamento().getVagasDeMotos(),
                estabelecimento.getEstacionamento().getVagasDeMotoOcupadas()
        );
    }
}
