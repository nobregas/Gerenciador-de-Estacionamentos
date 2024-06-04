package br.com.fcamara.fcamaraestacionamentos.entities;

import br.com.fcamara.fcamaraestacionamentos.dto.estabelecimento.EstabelecimentoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;


@Document(collection = "estabelecimentos")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Estabelecimento {
    @Id
    private String id;

    private String nome;

    @Indexed(unique = true)
    private String cnpj;

    private String endereco;

    private String telefone;

    private Estacionamento estacionamento;

    public Estabelecimento(EstabelecimentoDTO dto) {
        this.nome = dto.nome();
        this.cnpj = dto.cnpj();
        this.endereco = dto.endereco();
        this.telefone = dto.telefone();

        this.estacionamento = new Estacionamento(
                new ArrayList<>(),
                0,
                0,
                dto.vagasDeMotos(),
                dto.vagasDeCarros()
        );
    }
}
