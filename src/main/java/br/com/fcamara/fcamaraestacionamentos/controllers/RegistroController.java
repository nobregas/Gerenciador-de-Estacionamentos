package br.com.fcamara.fcamaraestacionamentos.controllers;

import br.com.fcamara.fcamaraestacionamentos.entities.Veiculo;
import br.com.fcamara.fcamaraestacionamentos.exceptions.ExceptionMessage;
import br.com.fcamara.fcamaraestacionamentos.services.RegistroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registros")
@RequiredArgsConstructor
public class RegistroController {

    private final RegistroService registroService;

    @Operation(summary = "Deleta um registro por seu id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "registro deletado com sucesso",
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
                    description = "Registro não encontrado",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionMessage.class))
                    }
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirRegistro(
            @Parameter(description = "O id do registro a ser deletado")
            @PathVariable String id){
        registroService.excluirRegistro(id);
        return ResponseEntity.noContent().build();
    }
}
