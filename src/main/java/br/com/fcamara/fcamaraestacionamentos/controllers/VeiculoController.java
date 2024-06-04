package br.com.fcamara.fcamaraestacionamentos.controllers;

import br.com.fcamara.fcamaraestacionamentos.dto.veiculo.VeiculoDTO;
import br.com.fcamara.fcamaraestacionamentos.entities.Veiculo;
import br.com.fcamara.fcamaraestacionamentos.exceptions.ExceptionMessage;
import br.com.fcamara.fcamaraestacionamentos.services.VeiculoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veiculos")
@RequiredArgsConstructor
public class VeiculoController {

    private final VeiculoService veiculoService;

    @Operation(summary = "Obtém todos os veiculos no sistema")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de veiculos",
                    content = {
                            @Content(mediaType = "application/json",
                            schema = @Schema(implementation = List.class))
                    }
            )
    })
    @GetMapping
    public ResponseEntity<List<Veiculo>> getAll() {
        return ResponseEntity.ok(veiculoService.findAll());
    }

    @Operation(summary = "Obtem um veiculo por seu id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Veiculo achado com sucesso",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Veiculo.class))
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
                    description = "Veiculo não encontrado",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionMessage.class))
                    }
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<Veiculo> getById(
            @Parameter(description = "O id do veiculo a ser encontrado")
            @PathVariable String id) {
        return ResponseEntity.ok(veiculoService.findById(id));
    }

    @Operation(summary = "Adiciona um veiculo ao sistema")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Veiculo criado com sucesso",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Veiculo.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "A placa fornecida já esta cadastrada no sistema",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionMessage.class))
                    }
            )

    })
    @PostMapping
    public ResponseEntity<Veiculo> create(
            @Valid @RequestBody VeiculoDTO veiculoDTO) {
        return new ResponseEntity<>(veiculoService.save(veiculoDTO), HttpStatus.CREATED);
    }

    @Operation(summary = "Deleta um veiculo por seu id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Veiculo deletado com sucesso",
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
                    description = "Veiculo não encontrado",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionMessage.class))
                    }
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "O id do veiculo a ser deletado")
            @PathVariable String id) {
        veiculoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Atualiza um veiculo por seu id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Veiculo atualizado com sucesso",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Veiculo.class))
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
                    description = "A nova placa informada já esta registrada no sistema",
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
    @PatchMapping("/{id}")
    public ResponseEntity<Veiculo> update(
            @Parameter(description = "O id do veiculo a ser atualizado")
            @PathVariable String id, @RequestBody VeiculoDTO veiculoDTO) {
        return ResponseEntity.ok(veiculoService.update(id, veiculoDTO));
    }
}
