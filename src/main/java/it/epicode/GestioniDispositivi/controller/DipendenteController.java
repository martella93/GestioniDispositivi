package it.epicode.GestioniDispositivi.controller;

import it.epicode.GestioniDispositivi.dto.DipendenteDto;
import it.epicode.GestioniDispositivi.exception.BadRequestException;
import it.epicode.GestioniDispositivi.exception.DipendenteNotFoundException;
import it.epicode.GestioniDispositivi.model.Dipendente;
import it.epicode.GestioniDispositivi.service.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
public class DipendenteController {

    @Autowired
    private DipendenteService dipendenteService;

    @PostMapping("/api/dipendente")
    public String saveDipendente(@RequestBody @Validated DipendenteDto dipendenteDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().
                    stream().map(objectError -> objectError.getDefaultMessage()).
                    reduce("",((s, s2) -> s+s2)
            ));
        }
        return dipendenteService.saveDipendente(dipendenteDto);
    }

    @GetMapping("/api/dipendente")
    public Page<Dipendente> getDipendenti(@RequestParam(defaultValue = "0")  int page,
                                          @RequestParam(defaultValue = "10") int size,
                                          @RequestParam(defaultValue = "id") String sortBy){
        return dipendenteService.getDipendenti(page,size,sortBy);
    }



    @GetMapping("/api/dipendente/{id}")
    public Dipendente getDipendenteById(@PathVariable int id) {
        Optional<Dipendente> dipendenteOptional = dipendenteService.getDipendenteById(id);
        if (dipendenteOptional.isPresent()) {
            return dipendenteOptional.get();
        } else {
            throw new DipendenteNotFoundException("Dipendente con id " + id + " non trovato");
        }
    }

    @PutMapping("/api/dipendente/{id}")
    public Dipendente updateDipendente(@PathVariable int id, @RequestBody @Validated DipendenteDto dipendenteDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().
                    stream().map(objectError -> objectError.getDefaultMessage()).
                    reduce("",((s, s2) -> s+s2)));
        }
        return dipendenteService.updateDipendente(id,dipendenteDto);
    }

    @DeleteMapping("/api/dipendente/{id}")
    public String deleteDipendente(@PathVariable int id){
        return dipendenteService.deleteDipendente(id);
    }

    @PatchMapping("/api/dipendente/{id}")
    public String uploadImmagineDipendente(@PathVariable int id, @RequestBody MultipartFile immagine) throws IOException {
        return dipendenteService.uploadImmagineDipendente(id, immagine);
    }
}
