package br.com.fcamara.fcamaraestacionamentos.controllers;

import br.com.fcamara.fcamaraestacionamentos.dto.estabelecimento.EstabelecimentoDTO;
import br.com.fcamara.fcamaraestacionamentos.dto.estabelecimento.EstabelecimentoDetailDTO;
import br.com.fcamara.fcamaraestacionamentos.dto.estabelecimento.EstabelecimentoEditDTO;
import br.com.fcamara.fcamaraestacionamentos.dto.estabelecimento.EstabelecimentoResponseDTO;
import br.com.fcamara.fcamaraestacionamentos.dto.registro.RegistroResponse;
import br.com.fcamara.fcamaraestacionamentos.entities.Estabelecimento;
import br.com.fcamara.fcamaraestacionamentos.exceptions.ExceptionMessage;
import br.com.fcamara.fcamaraestacionamentos.services.EstabelecimentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estabelecimentos")
@RequiredArgsConstructor
public class EstabelecimentoController {

    private final EstabelecimentoService estabelecimentoService;

    @Operation(summary = "Lista todos os estabeleimentos no sistema")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Estabelecimentos listados com sucesso",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = List.class))
                    }
            )
    })
    @GetMapping
    public ResponseEntity<List<EstabelecimentoResponseDTO>> findAll() {
        return ResponseEntity.ok(estabelecimentoService.listAll());
    }

    @Operation(summary = "Obtém os dados de um estabelecimento pelo seu id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Estabelecimento achado com sucesso",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = EstabelecimentoDetailDTO.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Id fornecido é inválido",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionMessage.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Estabelecimento não encontrado",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionMessage.class))
                    }
            )

    })
    @GetMapping("/{id}")
    public ResponseEntity<EstabelecimentoDetailDTO> findById(
            @Parameter(description = "O id do estabelecimento a ser detalhado")
            @PathVariable String id) {
        return ResponseEntity.ok(estabelecimentoService.getById(id));
    }

    @Operation(summary = "Cadastra um novo estabelecimento no sistema")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Estabelecimento cadastrado com sucesso",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Estabelecimento.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "O cnpj fornecido já está em uso",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionMessage.class))
                    }
            )

    })
    @PostMapping
    public ResponseEntity<Estabelecimento> save(@Valid @RequestBody EstabelecimentoDTO estabelecimentoDTO) {
        return new ResponseEntity<>(estabelecimentoService.save(estabelecimentoDTO),
                HttpStatus.CREATED);
    }

    @Operation(summary = "Obtém os dados de um estabelecimento pelo seu id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Estabelecimento achado com sucesso",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Estabelecimento.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Id fornecido é inválido",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionMessage.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "O novo cnpj informado ja está em uso",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionMessage.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Estabelecimento não encontrado",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionMessage.class))
                    }
            )

    })
    @PatchMapping("/{id}")
    public ResponseEntity<Estabelecimento> update(@RequestBody EstabelecimentoEditDTO estabelecimentoDTO
            , @Parameter(description = "O id do estabelecimento a ser editado") @PathVariable String id) {

        return ResponseEntity.ok(estabelecimentoService.update(estabelecimentoDTO, id));
    }

    @Operation(summary = "Deleta um estabelecimento por seu id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Estabelecimento deletado com sucesso",
                    content = {
                            @Content(mediaType = "application/json")
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Id fornecido é inválido",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionMessage.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Estabelecimento não encontrado",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionMessage.class))
                    }
            )

    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "O id do estabelecimento a ser deletado do sistema")
            @PathVariable String id) {
        estabelecimentoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
