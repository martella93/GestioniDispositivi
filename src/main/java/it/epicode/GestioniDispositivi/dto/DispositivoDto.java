package it.epicode.GestioniDispositivi.dto;

import it.epicode.GestioniDispositivi.enums.StatoDispositivo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DispositivoDto {

    @NotNull
    private StatoDispositivo statoDispositivo;

    @NotBlank(message = "Il nome non può essere null o vuoto")
    private String nome;

    private int dipendenteId;

    private int dispositivoId;
}
