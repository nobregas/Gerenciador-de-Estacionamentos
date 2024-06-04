package br.com.fcamara.fcamaraestacionamentos.services;

import br.com.fcamara.fcamaraestacionamentos.entities.Registro;
import br.com.fcamara.fcamaraestacionamentos.entities.Veiculo;
import br.com.fcamara.fcamaraestacionamentos.exceptions.registros.RegistroIllegalDeleteException;
import br.com.fcamara.fcamaraestacionamentos.exceptions.registros.RegistroNotFoundException;
import br.com.fcamara.fcamaraestacionamentos.repositories.RegistroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RegistroService {

    private final RegistroRepository registroRepository;

    public Registro novoRegistro(Veiculo veiculo) {
        Registro registro = new Registro(veiculo);

        return registroRepository.save(registro);
    }

    public void excluirRegistro(String id) {
        var registro = registroRepository.findById(id).
                        orElseThrow(RegistroNotFoundException::new);

        if (registro.getVeiculo() == null) {
            throw new RegistroIllegalDeleteException();
        } else {
            registroRepository.delete(registro);
        }
    }

    public Registro gerarSaida(Registro registro) {
        registro.setSaida(LocalDateTime.now());
        return registroRepository.save(registro);
    }
}
