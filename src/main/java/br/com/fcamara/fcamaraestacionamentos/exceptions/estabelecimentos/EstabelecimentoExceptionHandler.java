package br.com.fcamara.fcamaraestacionamentos.exceptions.estabelecimentos;

import br.com.fcamara.fcamaraestacionamentos.exceptions.ExceptionMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class EstabelecimentoExceptionHandler {
    @ResponseBody
    @ExceptionHandler(EstabelecimentoNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionMessage handleEstabelecimentoNotFoundException(EstabelecimentoNotFoundException ex) {
        return new ExceptionMessage(
                HttpStatus.NOT_FOUND.value(),
                "Estabelecimento não encontrado",
                LocalDateTime.now()
        );
    }

    @ResponseBody
    @ExceptionHandler(VagasCheiasException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionMessage handleVagaCheia(VagasCheiasException ex) {
        return new ExceptionMessage(
                HttpStatus.BAD_REQUEST.value(),
                "As vagas desse tipo de veiculo ja estão cheias!",
                LocalDateTime.now()
        );
    }
}
