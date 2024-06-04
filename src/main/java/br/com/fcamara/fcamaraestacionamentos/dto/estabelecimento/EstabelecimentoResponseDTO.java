package br.com.fcamara.fcamaraestacionamentos.dto.estabelecimento;

import br.com.fcamara.fcamaraestacionamentos.entities.Estabelecimento;

public record EstabelecimentoResponseDTO(
        String id,

        String nome,

        String cnpj,

        String endereco,

        String telefone
) {
    public EstabelecimentoResponseDTO(Estabelecimento estabelecimento) {
        this(
                estabelecimento.getId(),
                estabelecimento.getNome(),
                estabelecimento.getCnpj(),
                estabelecimento.getEndereco(),
                estabelecimento.getTelefone()
        );
    }
}
