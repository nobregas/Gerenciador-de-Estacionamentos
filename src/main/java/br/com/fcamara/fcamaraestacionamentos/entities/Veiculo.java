package br.com.fcamara.fcamaraestacionamentos.entities;

import br.com.fcamara.fcamaraestacionamentos.dto.veiculo.VeiculoDTO;
import br.com.fcamara.fcamaraestacionamentos.entities.enums.Tipo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "veiculos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Veiculo {
    @Id
    private String id;

    private String marca;

    private String modelo;

    @Indexed(unique = true)
    private String placa;

    private String cor;

    private Tipo tipo;

    public Veiculo(VeiculoDTO veiculoDTO) {
        this.marca = veiculoDTO.marca();
        this.modelo = veiculoDTO.modelo();
        this.placa = veiculoDTO.placa();
        this.cor = veiculoDTO.cor();
        this.tipo = veiculoDTO.tipo();
    }
}
