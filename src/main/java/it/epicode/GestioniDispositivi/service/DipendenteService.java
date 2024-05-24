package it.epicode.GestioniDispositivi.service;

import com.cloudinary.Cloudinary;
import it.epicode.GestioniDispositivi.dto.DipendenteDto;
import it.epicode.GestioniDispositivi.exception.DipendenteNotFoundException;
import it.epicode.GestioniDispositivi.model.Dipendente;
import it.epicode.GestioniDispositivi.repository.DipendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@Service
public class DipendenteService {

    @Autowired
    private DipendenteRepository dipendenteRepository;

    @Autowired
    private Cloudinary cloudinary;

    public String saveDipendente(DipendenteDto dipendenteDto){
        Dipendente dipendente = new Dipendente();
        dipendente.setNome(dipendenteDto.getNome());
        dipendente.setCognome(dipendenteDto.getCognome());
        dipendente.setUsername(dipendenteDto.getUsername());
        dipendente.setEmail(dipendenteDto.getEmail());

        dipendenteRepository.save(dipendente);
        return "Dipendete con id " + dipendente.getId() + " creato con successo";
    }

    public Page<Dipendente> getDipendenti(int page, int size, String sortBy){
        Pageable pageable = PageRequest.of(page,size, Sort.by(sortBy));
        return dipendenteRepository.findAll(pageable);
    }

    public Optional<Dipendente> getDipendenteById(int id){
        return dipendenteRepository.findById(id);
    }

    public Dipendente updateDipendente(int id, DipendenteDto dipendenteDto){
        Optional<Dipendente> dipendenteOptional = getDipendenteById(id);

        if (dipendenteOptional.isPresent()){
            Dipendente dipendente = dipendenteOptional.get();
            dipendente.setNome(dipendenteDto.getNome());
            dipendente.setCognome(dipendenteDto.getCognome());
            dipendente.setUsername(dipendenteDto.getUsername());
            dipendente.setEmail(dipendenteDto.getEmail());

            return dipendenteRepository.save(dipendente);
        }
        else {
            throw new DipendenteNotFoundException("Dipendente con id " + id + " non trovato");
        }
    }

    public String deleteDipendente(int id){
        Optional<Dipendente> dipendenteOptional = getDipendenteById(id);

        if (dipendenteOptional.isPresent()){
            dipendenteRepository.delete(dipendenteOptional.get());
            return "Dipendente con id " + id + " cancellato";
        }
        else {
            throw new DipendenteNotFoundException("Dipendente con id " + id + " non trovato");
        }
    }

    public String uploadImmagineDipendente(int id, MultipartFile cover) throws IOException {
        Optional<Dipendente> dipendenteOptional = getDipendenteById(id);

        if(dipendenteOptional.isPresent()){
            String url = (String)cloudinary.uploader().upload(cover.getBytes(), Collections.emptyMap()).get("url");
            Dipendente dipendente = dipendenteOptional.get();
            dipendente.setImmagine(url);

            dipendenteRepository.save(dipendente);
            return "Immagine con url=" + url + " salvata correttamente su dipendente con id=" + id;
        }
        else{
            throw new DipendenteNotFoundException("Dipendente con " + id + " non trovato");
        }
    }
}
