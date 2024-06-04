package br.com.fcamara.fcamaraestacionamentos.services;

import br.com.fcamara.fcamaraestacionamentos.dto.estacionamento.EstacionamentoResponseDTO;
import br.com.fcamara.fcamaraestacionamentos.dto.registro.RegistroResponse;
import br.com.fcamara.fcamaraestacionamentos.entities.Estabelecimento;
import br.com.fcamara.fcamaraestacionamentos.entities.Estacionamento;
import br.com.fcamara.fcamaraestacionamentos.entities.Registro;
import br.com.fcamara.fcamaraestacionamentos.entities.Veiculo;
import br.com.fcamara.fcamaraestacionamentos.entities.enums.Tipo;
import br.com.fcamara.fcamaraestacionamentos.exceptions.estabelecimentos.EstabelecimentoNotFoundException;
import br.com.fcamara.fcamaraestacionamentos.exceptions.estabelecimentos.VagasCheiasException;
import br.com.fcamara.fcamaraestacionamentos.exceptions.veiculos.VeiculoJaEstacionadoException;
import br.com.fcamara.fcamaraestacionamentos.exceptions.veiculos.VeiculoJaSaiuException;
import br.com.fcamara.fcamaraestacionamentos.exceptions.veiculos.VeiculoNotFoundException;
import br.com.fcamara.fcamaraestacionamentos.repositories.EstabelecimentoRepository;
import br.com.fcamara.fcamaraestacionamentos.repositories.VeiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstacionamentoService {

    private final EstabelecimentoRepository estabelecimentoRepository;
    private final VeiculoRepository veiculoRepository;

    private final RegistroService registroService;


    private Estabelecimento getEstabelecimento(String estabelecimentoId) {
        Estabelecimento estabelecimento = estabelecimentoRepository.findById(estabelecimentoId)
                .orElseThrow(EstabelecimentoNotFoundException::new);
        return estabelecimento;
    }

    private Veiculo getVeiculo(String veiculoId) {
        return veiculoRepository.findById(veiculoId)
                .orElseThrow(VeiculoNotFoundException::new);
    }

    public EstacionamentoResponseDTO getEstacionameto(String estabelecimentoId) {
        var estabelecimento = getEstabelecimento(estabelecimentoId);

        Estacionamento estacionamento = estabelecimento.getEstacionamento();

        return new EstacionamentoResponseDTO(estacionamento);
    }

    public List<Registro> listAllRegistros(String estabelecimentoId) {
        var estabelecimento = getEstabelecimento(estabelecimentoId);
        return estabelecimento.getEstacionamento().getRegistros();
    }

    public RegistroResponse registrarEntrada(String estabelecimentoId, String veiculoId) {
        var veiculo = getVeiculo(veiculoId);
        var estabelecimento = getEstabelecimento(estabelecimentoId);
        var estacionamento = estabelecimento.getEstacionamento();

        if (verificarDisponibilidade(veiculo, estacionamento)) {
            if(estaEstacionado(veiculo, estacionamento)) {
                Registro registro = registroService.novoRegistro(veiculo);

                estacionamento.getRegistros().add(registro);
                estabelecimentoRepository.save(estabelecimento);
                return new RegistroResponse(estabelecimento, registro);
            } else {
                throw new VeiculoJaEstacionadoException();
            }

        } else {
            Tipo tipo = veiculo.getTipo();
            throw new VagasCheiasException();
        }
    }

    public RegistroResponse registrarSaida(String estabelecimentoId, String veiculoId) {
        var veiculo = getVeiculo(veiculoId);
        var estabelecimento = getEstabelecimento(estabelecimentoId);
        var estacionamento = estabelecimento.getEstacionamento();

        //Se o veiculo ja estiver registrado poderá sair
        if(!estaEstacionado(veiculo, estacionamento)) {

            if ((veiculo.getTipo() == Tipo.CARRO)) {
                estacionamento.desocuparVagaDeCarro();
            } else {
                estacionamento.desocuparVagaDeMoto();
            }

            var registro = obterRegistro(estacionamento, veiculo);
            registro = registroService.gerarSaida(registro);

            estabelecimentoRepository.save(estabelecimento);

            return new RegistroResponse(estabelecimento, registro);
        } else {
            throw new VeiculoJaSaiuException();
        }
    }

    private boolean verificarDisponibilidade(Veiculo veiculo, Estacionamento estacionamento) {
        boolean disponibilidade = false;

        if(veiculo.getTipo().equals(Tipo.CARRO)) {
            int vagasDeCarrosTotais = estacionamento.getVagasDeCarros();
            int vagasDeCarroOcupadas = estacionamento.getVagasDeCarroOcupadas();

            if (vagasDeCarroOcupadas != vagasDeCarrosTotais){
                estacionamento.ocuparVagaDeCarro();
                disponibilidade = true;
            }

        } else {
            int vagasDeMotosTotais = estacionamento.getVagasDeMotos();
            int vagasDeMotoOcupadas = estacionamento.getVagasDeMotoOcupadas();

            if(vagasDeMotosTotais != vagasDeMotoOcupadas) {
                estacionamento.ocuparVagaDeCarro();
                disponibilidade = true;
            }

        }
        return disponibilidade;
    }

    private boolean estaEstacionado(Veiculo veiculo, Estacionamento estacionamento) {
        boolean estacionado = false;
        var veiculoID = veiculo.getId();
        var registros = estacionamento.getRegistros();

        if (registros.isEmpty()) {
            estacionado = true;
        } else {
            for (Registro registro : registros) {
                var veiculoRegistrado = registro.getVeiculo();
                if (veiculoID.equals(veiculoRegistrado.getId())) {
                    //Verifica se saiu
                    if(registro.getSaida() != null) {
                        estacionado = true;
                        break;
                    }
                }
            }
        }
        return estacionado;
    }

    private Registro obterRegistro(Estacionamento estacionamento, Veiculo veiculo) {
        Registro registro = null;
        for (Registro registro_ : estacionamento.getRegistros()) {
            if (registro_.getVeiculo().equals(veiculo)) {
                registro = registro_;
            }
        }
        return registro;
    }
}
