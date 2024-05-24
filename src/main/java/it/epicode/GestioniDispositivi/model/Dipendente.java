package it.epicode.GestioniDispositivi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;


import java.util.Set;


@Data
@Entity
public class Dipendente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome;
    private String cognome;
    private String username;
    private String email;
    private String immagine;

    @OneToMany(mappedBy = "dipendente")
    @JsonIgnore
    private Set<Dispositivo> dispositivi;
}
