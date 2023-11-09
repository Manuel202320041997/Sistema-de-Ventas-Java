package Exceptions;

public class BaseDeDatosException extends Exception {
    public BaseDeDatosException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
