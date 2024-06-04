package br.com.fcamara.fcamaraestacionamentos.services;

import br.com.fcamara.fcamaraestacionamentos.dto.estabelecimento.EstabelecimentoDTO;
import br.com.fcamara.fcamaraestacionamentos.dto.estabelecimento.EstabelecimentoDetailDTO;
import br.com.fcamara.fcamaraestacionamentos.dto.estabelecimento.EstabelecimentoEditDTO;
import br.com.fcamara.fcamaraestacionamentos.dto.estabelecimento.EstabelecimentoResponseDTO;
import br.com.fcamara.fcamaraestacionamentos.entities.Estabelecimento;
import br.com.fcamara.fcamaraestacionamentos.exceptions.estabelecimentos.EstabelecimentoNotFoundException;
import br.com.fcamara.fcamaraestacionamentos.exceptions.validation.InvalidCnpjException;
import br.com.fcamara.fcamaraestacionamentos.repositories.EstabelecimentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstabelecimentoService {

    private final EstabelecimentoRepository estabelecimentoRepository;

    public List<EstabelecimentoResponseDTO> listAll(){
        return estabelecimentoRepository.findAll()
                .stream()
                .map(EstabelecimentoResponseDTO::new)
                .toList();
    }

    public EstabelecimentoDetailDTO getById(String id){
        var estabelecimento = estabelecimentoRepository.findById(id)
                .orElseThrow(EstabelecimentoNotFoundException::new);
        return new EstabelecimentoDetailDTO(estabelecimento);
    }

    public Estabelecimento save(EstabelecimentoDTO dto){
        if (cnpjExistente(dto.cnpj())) {
            throw new InvalidCnpjException();
        } else {
            var estabelecimento = new Estabelecimento(dto);
            return estabelecimentoRepository.save(estabelecimento);
        }
    }

    public Estabelecimento update(EstabelecimentoEditDTO dto, String id){
        var estabelecimento = estabelecimentoRepository.findById(id)
                .orElseThrow(EstabelecimentoNotFoundException::new);

        atualizarAtributos(dto, estabelecimento);

        return estabelecimentoRepository.save(estabelecimento);
    }

    public void delete(String id){
        boolean exists = estabelecimentoRepository.existsById(id);

        if(!exists)
            throw new EstabelecimentoNotFoundException();

        estabelecimentoRepository.deleteById(id);
    }

    private void atualizarAtributos(EstabelecimentoEditDTO dto, Estabelecimento model){
        if(!dto.nome().isBlank()
                && !model.getNome().equals(dto.nome())) {
            model.setNome(dto.nome());
        }
        if(!dto.cnpj().isBlank()
                && !model.getCnpj().equals(dto.cnpj())) {

            if(cnpjExistente(dto.cnpj())) {
                throw new InvalidCnpjException();
            } else {
                model.setCnpj(dto.cnpj());
            }
        }
        if(!dto.endereco().isBlank()
                && !model.getEndereco().equals(dto.endereco())) {
            model.setEndereco(dto.endereco());
        }
        if(!dto.telefone().isBlank()
                && !model.getTelefone().equals(dto.telefone())) {
            model.setTelefone(dto.telefone());
        }

    }

    private boolean cnpjExistente(String cnpj){
        return estabelecimentoRepository.existsByCnpj(cnpj);
    }


}
