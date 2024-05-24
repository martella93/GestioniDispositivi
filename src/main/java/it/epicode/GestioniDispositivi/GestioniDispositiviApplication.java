package it.epicode.GestioniDispositivi;

import it.epicode.GestioniDispositivi.dto.DispositivoDto;
import it.epicode.GestioniDispositivi.repository.DipendenteRepository;
import it.epicode.GestioniDispositivi.repository.DispositivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

@SpringBootApplication
public class GestioniDispositiviApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestioniDispositiviApplication.class, args);
	}


}
