package br.com.fcamara.fcamaraestacionamentos.exceptions.registros;

import br.com.fcamara.fcamaraestacionamentos.exceptions.ExceptionMessage;
import br.com.fcamara.fcamaraestacionamentos.exceptions.estabelecimentos.VagasCheiasException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class RegistroExceptionHandler {

    @ResponseBody
    @ExceptionHandler(RegistroNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionMessage handleRegistroNotFoundException(RegistroNotFoundException ex) {
        return new ExceptionMessage(
                HttpStatus.NOT_FOUND.value(),
                "Registro não encontrado",
                LocalDateTime.now()
        );
    }

    @ResponseBody
    @ExceptionHandler(RegistroIllegalDeleteException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionMessage handleRegistroIllegalDeleteException(RegistroIllegalDeleteException ex) {
        return new ExceptionMessage(
                HttpStatus.BAD_REQUEST.value(),
                "O veiculo do registro ja deve ter saido para deletar",
                LocalDateTime.now()
        );
    }
}
