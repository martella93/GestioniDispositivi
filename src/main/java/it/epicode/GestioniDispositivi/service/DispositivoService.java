package it.epicode.GestioniDispositivi.service;

import it.epicode.GestioniDispositivi.dto.DispositivoDto;
import it.epicode.GestioniDispositivi.exception.DispositivoNotFoundException;
import it.epicode.GestioniDispositivi.model.Dipendente;
import it.epicode.GestioniDispositivi.model.Dispositivo;
import it.epicode.GestioniDispositivi.repository.DipendenteRepository;
import it.epicode.GestioniDispositivi.repository.DispositivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class DispositivoService {

    @Autowired
    private DispositivoRepository dispositivoRepository;
    @Autowired
    private DipendenteRepository dipendenteRepository;

    public String saveDispositivo(DispositivoDto dispositivoDto){

        Dispositivo dispositivo = new Dispositivo();
        dispositivo.setNome(dispositivoDto.getNome());
        dispositivo.setStatoDispositivo(dispositivoDto.getStatoDispositivo());
        dispositivoRepository.save(dispositivo);

        return "Dispositivo con id " + dispositivo.getId() + " salvato correttamente";
    }

    public List<Dispositivo> getDispositivi(){
        return dispositivoRepository.findAll();
    }

    public Optional<Dispositivo> getDispositivoById(int id){
        return dispositivoRepository.findById(id);
    }

    public Dispositivo updateDispositivo(int id, DispositivoDto dispositivoDto){
        Optional<Dispositivo> dispositivoOptional = getDispositivoById(id);

        if (dispositivoOptional.isPresent()){
            Dispositivo dispositivo = dispositivoOptional.get();
            dispositivo.setNome(dispositivoDto.getNome());
            dispositivo.setStatoDispositivo(dispositivoDto.getStatoDispositivo());
            return dispositivoRepository.save(dispositivo);

//            Optional<Dipendente> dipendenteOptional = dipendenteRepository.findById(dispositivoDto.getDipendenteId());
//
//            if (dipendenteOptional.isPresent()){
//                dispositivo.setDipendente(dipendenteOptional.get());
//                return dispositivoRepository.save(dispositivo);
//            }
//            else {
//                throw new DipendenteNotFoundException("Dipendente con id " + id + " non trovato");
//            }

        }
        else {
            throw new DispositivoNotFoundException("Dispositivo con id " + id+ " non trovato");
        }
    }

    public String deleteDispositvi(int id){
        Optional<Dispositivo> dispositivoOptional = getDispositivoById(id);

        if (dispositivoOptional.isPresent()){
            dispositivoRepository.delete(dispositivoOptional.get());
            return "Dispositivo con id " + id + " cancellato";
        }
        else {
            throw new DispositivoNotFoundException("Dispositivo con id " + id + " non trovato");
        }
    }

    public String assegnaDispositivo(int dipendenteId, int dispositivoId) {
        Optional<Dipendente> dipendenteOptional = dipendenteRepository.findById(dipendenteId);
        Optional<Dispositivo> dispositivoOptional = dispositivoRepository.findById(dispositivoId);

        if (dipendenteOptional.isPresent() && dispositivoOptional.isPresent()) {
            Dipendente dipendente = dipendenteOptional.get();
            Dispositivo dispositivo = dispositivoOptional.get();

            dispositivo.setDipendente(dipendente);
           // dipendente.setDispositivi(Set<Dispositivo>);
            dispositivoRepository.save(dispositivo);

            return "Dispositivo assegnato al dipendente con successo.";
        } else {
            throw new IllegalArgumentException("Dipendente o dispositivo non trovato.");
        }
    }
}

