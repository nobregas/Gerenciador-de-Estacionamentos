package br.com.fcamara.fcamaraestacionamentos.dto.estabelecimento;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CNPJ;

public record EstabelecimentoDTO(

        @NotNull(message = "O campo nome é obrigatório")
        @Size(min = 2, max = 15, message = "nome inválido")
        String nome,

        @NotNull(message = "O campo cnpj é obrigatório")
        @CNPJ(message = "cnpj inválido")
        String cnpj,

        @NotNull(message = "O campo endereco é obrigatório")
        String endereco,

        @NotNull(message = "O campo telefone é obrigatório")
        @Size(min = 11, message = "telefone inválido")
        String telefone,

        @NotNull(message = "O campo vaga de motos é obrigatório")
        @Min(value = 0, message = "Vaga de motos deve ser maior ou igual a 0")
        int vagasDeMotos,

        @NotNull(message = "O campo vaga de carros é obrigatório")
        @Min(value = 0, message = "Vaga de carros deve ser maior ou igual a 0")
        int vagasDeCarros

) {
}
