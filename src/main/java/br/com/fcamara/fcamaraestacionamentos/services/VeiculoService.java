package br.com.fcamara.fcamaraestacionamentos.services;

import br.com.fcamara.fcamaraestacionamentos.dto.veiculo.VeiculoDTO;
import br.com.fcamara.fcamaraestacionamentos.entities.Veiculo;
import br.com.fcamara.fcamaraestacionamentos.entities.enums.Tipo;
import br.com.fcamara.fcamaraestacionamentos.exceptions.estabelecimentos.EstabelecimentoNotFoundException;
import br.com.fcamara.fcamaraestacionamentos.exceptions.veiculos.PlacaJaExistenteException;
import br.com.fcamara.fcamaraestacionamentos.exceptions.veiculos.VeiculoNotFoundException;
import br.com.fcamara.fcamaraestacionamentos.repositories.VeiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;

    public List<Veiculo> findAll() {
        return veiculoRepository.findAll();
    }

    public Veiculo findById(String id) {
        var veiculo = veiculoRepository.findById(id)
                .orElseThrow(VeiculoNotFoundException::new);

        return veiculo;
    }

    public Veiculo save(VeiculoDTO veiculoDTO) {
        if (placaExistente(veiculoDTO.placa())) {
            throw new PlacaJaExistenteException();
        }
        var veiculo = new Veiculo(veiculoDTO);
        return veiculoRepository.save(veiculo);
    }

    public void delete(String id) {
        boolean exists = veiculoRepository.existsById(id);

        if (!exists)
            throw new VeiculoNotFoundException();

        veiculoRepository.deleteById(id);
    }

    public Veiculo update(String id, VeiculoDTO veiculoDTO) {
        var veiculoOptional = veiculoRepository.findById(id);

        if(veiculoOptional.isEmpty())
            throw new VeiculoNotFoundException();

        var veiculo = veiculoOptional.get();

        atualizarAtributos(veiculoDTO, veiculo);

        return veiculoRepository.save(veiculo);
    }

    private void atualizarAtributos(VeiculoDTO veiculoDTO, Veiculo veiculo) {
        if(!veiculoDTO.marca().isBlank()
                && !veiculoDTO.marca().equals(veiculo.getMarca())) {

            veiculo.setMarca(veiculoDTO.marca());
        }
        if(!veiculoDTO.modelo().isBlank()
                && !veiculoDTO.modelo().equals(veiculo.getModelo())) {

            veiculo.setModelo(veiculoDTO.modelo());
        }
        if(!veiculoDTO.cor().isBlank()
                && !veiculoDTO.cor().equals(veiculo.getCor())) {

            veiculo.setCor(veiculoDTO.cor());
        }
        if(!veiculoDTO.placa().isBlank()
                && !veiculoDTO.placa().equals(veiculo.getPlaca())) {

            if(placaExistente(veiculoDTO.placa())) {
                throw new PlacaJaExistenteException();
            } else {
                veiculo.setPlaca(veiculoDTO.placa());
            }
        }
        if(veiculoDTO.tipo() != null
                && !veiculoDTO.tipo().equals(veiculo.getTipo())) {

            veiculo.setTipo(veiculoDTO.tipo());
        }
    }

    private boolean placaExistente(String placa) {
        return veiculoRepository.existsByPlaca(placa);
    }
}
