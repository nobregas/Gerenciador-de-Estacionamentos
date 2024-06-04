package br.com.fcamara.fcamaraestacionamentos.controllers;

import br.com.fcamara.fcamaraestacionamentos.dto.estacionamento.EstacionamentoResponseDTO;
import br.com.fcamara.fcamaraestacionamentos.dto.registro.RegistrarEntradaDTO;
import br.com.fcamara.fcamaraestacionamentos.dto.registro.RegistroResponse;
import br.com.fcamara.fcamaraestacionamentos.entities.Registro;
import br.com.fcamara.fcamaraestacionamentos.entities.Veiculo;
import br.com.fcamara.fcamaraestacionamentos.exceptions.ExceptionMessage;
import br.com.fcamara.fcamaraestacionamentos.services.EstacionamentoService;
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
@RequiredArgsConstructor
@RequestMapping("/estacionamento")
public class EstacionamentoController {

    private final EstacionamentoService estacionamentoService;

    @Operation(summary = "Obtém os dados de um estacionamento por seu id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Estacionamento achado com sucesso",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = EstacionamentoResponseDTO.class))
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
                    description = "Estacionamento não encontrado",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionMessage.class))
                    }
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<EstacionamentoResponseDTO> getEstacionamento(
            @Parameter(description = "O id do estacionamento a ser encontrado")
            @PathVariable String id) {
        return ResponseEntity.ok(estacionamentoService.getEstacionameto(id));
    }

    @Operation(summary = "Obtém os registros de um estacionamento por seu id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Registros listados com sucesso",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = List.class))
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
                    description = "Estacionamento não encontrado",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionMessage.class))
                    }
            )
    })
    @GetMapping("/{id}/registros")
    public ResponseEntity<List<Registro>> getRegistros(
            @Parameter(description = "O id do estacionamento que deseja ver os registros")
            @PathVariable String id) {
        return ResponseEntity.ok(estacionamentoService.listAllRegistros(id));
    }

    @Operation(summary = "Registra a entrada de um veiculo no estacionamento")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Entrada registrada com sucesso",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = RegistroResponse.class))
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
                    description = "Veiculo já está estacionado",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionMessage.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Não há vagas para o tipo de veiculo",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionMessage.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Estacionamento não encontrado",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionMessage.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Veiculo não encontrado",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionMessage.class))
                    }
            )

    })
    @PostMapping("/registrarEntrada")
    public ResponseEntity<RegistroResponse> registrarEntrada(@RequestBody @Valid RegistrarEntradaDTO registrarEntradaDTO) {
        return new ResponseEntity<>(estacionamentoService.registrarEntrada(
                registrarEntradaDTO.estacionamentoId()
                ,registrarEntradaDTO.veiculoId()
        ),
                HttpStatus.CREATED);
    }

    @Operation(summary = "Registra a saida de um veiculo no estacionamento")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Saida registrada com sucesso",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = RegistroResponse.class))
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
                    description = "Veiculo já saiu do estacionamento",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionMessage.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Estacionamento não encontrado",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionMessage.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Veiculo não encontrado",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionMessage.class))
                    }
            )

    })
    @PostMapping("/registrarSaida")
    public ResponseEntity<RegistroResponse> registrarSaida(@RequestBody @Valid RegistrarEntradaDTO registrarEntradaDTO) {
        return ResponseEntity.ok(estacionamentoService.registrarSaida(
                registrarEntradaDTO.estacionamentoId()
                ,registrarEntradaDTO.veiculoId()
        ));

    }
}
