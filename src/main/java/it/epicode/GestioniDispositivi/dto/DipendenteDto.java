package it.epicode.GestioniDispositivi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class DipendenteDto {

    @NotBlank(message = "Il nome non può essere null o vuoto")
    @Size(max = 50)
    private String nome;
    @NotBlank(message = "IL cognome non può essere null o vuoto")
    @Size(max = 50)
    private String cognome;
    @NotBlank(message = "L'username non può essere vuoto o null")
    @Size(max = 50)
    private String username;
    @NotBlank(message = "L'email non può essere null o vuoto")
    @Email
    private String email;
    private String immagine;
}
