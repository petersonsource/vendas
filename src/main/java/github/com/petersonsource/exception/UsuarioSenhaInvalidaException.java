package github.com.petersonsource.exception;

public class UsuarioSenhaInvalidaException extends RuntimeException{

    public UsuarioSenhaInvalidaException(){
        super("senha inválida");
    }
}
