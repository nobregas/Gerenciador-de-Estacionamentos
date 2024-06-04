package br.com.fcamara.fcamaraestacionamentos.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Estacionamento {

    @DBRef
    private List<Registro> registros;

    private int vagasDeCarroOcupadas;

    private int vagasDeMotoOcupadas;

    private int vagasDeMotos;

    private int vagasDeCarros;

    public void ocuparVagaDeMoto() {
        this.vagasDeMotoOcupadas++;
    }

    public void ocuparVagaDeCarro() {
        this.vagasDeCarroOcupadas++;
    }

    public void desocuparVagaDeMoto() {
        this.vagasDeMotoOcupadas--;
    }

    public void desocuparVagaDeCarro() {
        this.vagasDeCarroOcupadas--;
    }

}
