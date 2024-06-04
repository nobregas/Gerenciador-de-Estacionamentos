package br.com.fcamara.fcamaraestacionamentos.repositories;


import br.com.fcamara.fcamaraestacionamentos.entities.Veiculo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VeiculoRepository extends MongoRepository<Veiculo, String> {

    boolean existsByPlaca(String placa);

}
