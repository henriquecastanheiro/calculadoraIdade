package projeto.calculadora_idade.exception;

public class DataInvalidaException extends RuntimeException {
    public DataInvalidaException(String mensagem) {
        super(mensagem);
    }
}
