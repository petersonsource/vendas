package github.com.petersonsource.rest.controller;

import github.com.petersonsource.exception.RegraNegocioException;
import github.com.petersonsource.rest.ApiErros;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErros handleRegraNegocioException(RegraNegocioException ex){
        String mensagemErro = ex.getMessage();
        return new ApiErros(mensagemErro);
    }
}
