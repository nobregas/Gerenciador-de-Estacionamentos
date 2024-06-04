package br.com.fcamara.fcamaraestacionamentos.exceptions.veiculos;

import br.com.fcamara.fcamaraestacionamentos.exceptions.ExceptionMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class VeiculoExceptionHandler {

    @ResponseBody
    @ExceptionHandler(VeiculoNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionMessage handleVeiculoNotFoundException(VeiculoNotFoundException ex) {
        return new ExceptionMessage(
                HttpStatus.NOT_FOUND.value(),
                "Veiculo não encontrado",
                LocalDateTime.now()
        );
    }

    @ResponseBody
    @ExceptionHandler(VeiculoJaEstacionadoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionMessage handleVeiculoJaRegistradoException(VeiculoJaEstacionadoException ex) {
        return new ExceptionMessage(
                HttpStatus.BAD_REQUEST.value(),
                "Veiculo já esta estacionado!",
                LocalDateTime.now()
        );
    }

    @ResponseBody
    @ExceptionHandler(PlacaJaExistenteException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionMessage handlePlacaJaExistenteException(PlacaJaExistenteException ex) {
        return new ExceptionMessage(
                HttpStatus.BAD_REQUEST.value(),
                "Já existe um veiculo registrado com essa placa!",
                LocalDateTime.now()
        );
    }

    @ResponseBody
    @ExceptionHandler(VeiculoJaSaiuException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionMessage handleVeiculoJaSaiuException(VeiculoJaSaiuException ex) {
        return new ExceptionMessage(
                HttpStatus.BAD_REQUEST.value(),
                "O veiculo já saiu!",
                LocalDateTime.now()
        );
    }
}
