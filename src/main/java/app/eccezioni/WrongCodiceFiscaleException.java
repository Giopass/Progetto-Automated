package app.eccezioni;

public class WrongCodiceFiscaleException extends Exception {

    public WrongCodiceFiscaleException(String message) {
        super(message);
    }
}