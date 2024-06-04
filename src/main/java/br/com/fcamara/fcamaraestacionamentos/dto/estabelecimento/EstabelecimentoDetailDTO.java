package br.com.fcamara.fcamaraestacionamentos.dto.estabelecimento;

import br.com.fcamara.fcamaraestacionamentos.entities.Estabelecimento;

public record EstabelecimentoDetailDTO(
        String id,

        String nome,

        String cnpj,

        String endereco,

        String telefone,

        int vagasDeMotos,

        int vagasDeCarros
) {
    public EstabelecimentoDetailDTO(Estabelecimento estabelecimento) {
        this(
                estabelecimento.getId(),
                estabelecimento.getNome(),
                estabelecimento.getCnpj(),
                estabelecimento.getEndereco(),
                estabelecimento.getTelefone(),
                estabelecimento.getEstacionamento().getVagasDeMotos(),
                estabelecimento.getEstacionamento().getVagasDeCarros()
        );
    }
}
