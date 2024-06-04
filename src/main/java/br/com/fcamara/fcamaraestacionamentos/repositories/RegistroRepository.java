package br.com.fcamara.fcamaraestacionamentos.repositories;

import br.com.fcamara.fcamaraestacionamentos.entities.Registro;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RegistroRepository
extends MongoRepository<Registro, String> {
}
