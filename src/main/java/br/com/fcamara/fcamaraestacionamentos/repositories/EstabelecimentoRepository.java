package br.com.fcamara.fcamaraestacionamentos.repositories;

import br.com.fcamara.fcamaraestacionamentos.entities.Estabelecimento;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EstabelecimentoRepository
extends MongoRepository<Estabelecimento, String> {
    boolean existsByCnpj(String cnpj);
}
