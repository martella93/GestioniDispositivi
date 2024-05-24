package it.epicode.GestioniDispositivi.repository;

import it.epicode.GestioniDispositivi.model.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DipendenteRepository extends JpaRepository<Dipendente, Integer> {
}
