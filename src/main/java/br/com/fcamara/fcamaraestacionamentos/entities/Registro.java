package br.com.fcamara.fcamaraestacionamentos.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "registros")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Registro {
    @Id
    private String id;

    @DBRef
    private Veiculo veiculo;

    private LocalDateTime entrada;

    private LocalDateTime saida;

    public Registro(Veiculo veiculo) {
        this.veiculo = veiculo;
        this.entrada = LocalDateTime.now();
        this.saida = null;
    }
}
