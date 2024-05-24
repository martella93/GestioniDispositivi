package it.epicode.GestioniDispositivi.controller;

import it.epicode.GestioniDispositivi.dto.DispositivoDto;
import it.epicode.GestioniDispositivi.exception.BadRequestException;
import it.epicode.GestioniDispositivi.exception.DispositivoNotFoundException;
import it.epicode.GestioniDispositivi.model.Dispositivo;
import it.epicode.GestioniDispositivi.service.DispositivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class DispositivoController {

    @Autowired
    private DispositivoService dispositivoService;

    @PostMapping("/api/dispositivo")
    public String saveDispositivo(@RequestBody @Validated DispositivoDto dispositivoDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors().
                    stream().map(objectError -> objectError.getDefaultMessage()).
                    reduce("", ((s, s2) -> s + s2)
                    ));
        }
        return dispositivoService.saveDispositivo(dispositivoDto);
    }

    @GetMapping("/api/dispositivo")
    public List<Dispositivo> getDispositivi(){
        return dispositivoService.getDispositivi();
    }

    @GetMapping("/api/dispositivo/{id}")
    public Dispositivo dispositivoById(@PathVariable int id){
        Optional<Dispositivo> dispositivoOptional = dispositivoService.getDispositivoById(id);

        if (dispositivoOptional.isPresent()){
            return dispositivoOptional.get();
        }
        else {
            throw new DispositivoNotFoundException("Dispositivo con id " + id + " non trovato");
        }
    }

    @PutMapping("/api/dispositivo/{id}")
    public Dispositivo updateDispositivo(@PathVariable int id,@RequestBody @Validated DispositivoDto dispositivoDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors().
                    stream().map(objectError -> objectError.getDefaultMessage()).
                    reduce("", ((s, s2) -> s + s2)
                    ));
        }
        return dispositivoService.updateDispositivo(id, dispositivoDto);
    }

    @DeleteMapping("/api/dispositivo/{id}")
    public String deleteDispositivo(@PathVariable int id){
        return dispositivoService.deleteDispositvi(id);
    }

    @PostMapping("/api/dispositivo/assegnaDispositivo")
    public String assegnaDispositivo(@RequestParam int idDipendente, @RequestParam int idDispositivo) {
        dispositivoService.assegnaDispositivo(idDipendente, idDispositivo);
        return "Dispositivo assegnato al dipendente con id " +idDipendente;
    }
}
