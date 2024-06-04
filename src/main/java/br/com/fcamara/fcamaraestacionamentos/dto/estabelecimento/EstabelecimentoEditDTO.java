package br.com.fcamara.fcamaraestacionamentos.dto.estabelecimento;


public record EstabelecimentoEditDTO(
        String nome,

        String cnpj,

        String endereco,

        String telefone
) {
}
