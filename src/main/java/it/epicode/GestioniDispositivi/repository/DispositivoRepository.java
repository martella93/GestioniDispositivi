package it.epicode.GestioniDispositivi.repository;

import it.epicode.GestioniDispositivi.model.Dispositivo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DispositivoRepository extends JpaRepository<Dispositivo, Integer> {
}
