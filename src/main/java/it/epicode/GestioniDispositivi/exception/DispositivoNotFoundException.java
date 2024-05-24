package it.epicode.GestioniDispositivi.exception;

public class DispositivoNotFoundException extends RuntimeException{
    public DispositivoNotFoundException(String message){
        super(message);
    }
}
