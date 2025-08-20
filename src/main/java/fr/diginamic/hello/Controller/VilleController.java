package fr.diginamic.hello.Controller;

import fr.diginamic.hello.entities.Ville;
import fr.diginamic.hello.repositories.VilleRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ville")
public class VilleController {

    VilleRepository villeRepository;

    public VilleController(VilleRepository villeRepository) {
        this.villeRepository = villeRepository;
    }


    @GetMapping("/villes")
    public List<Ville> getVilles() {
       return villeRepository.findAll();
    }

    @PostMapping("/")
    public Ville addVille(@Valid @RequestBody Ville ville) {
        return villeRepository.save(ville);
    }

    @GetMapping("/{id}")
    public Ville getVilleById(@PathVariable Long id) {
        return villeRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Ville updateVille(@PathVariable Long id, @RequestBody Ville ville) {
        if (villeRepository.existsById(id)) {
            ville.setId(id);
            return villeRepository.save(ville);
        }
        return ville;
    }

    @DeleteMapping("/{id}")
    public void deleteVille(@PathVariable Long id) {
        if (villeRepository.existsById(id)) {
            villeRepository.deleteById(id);
        }
    }


}
