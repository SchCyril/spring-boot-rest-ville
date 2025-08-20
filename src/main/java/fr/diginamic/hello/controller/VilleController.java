package fr.diginamic.hello.controller;

import fr.diginamic.hello.entities.Ville;
import fr.diginamic.hello.services.VilleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ville")
public class VilleController {

   private final VilleService villeService;

    public VilleController(VilleService villeService) {
        this.villeService = villeService;
    }

    /**
     * Affiche la liste de toutes les villes
     * @return une liste de villes
     */

    @GetMapping("/villes")
    public List<Ville> getVilles() {
        return villeService.extractVilles();
    }

    /**
     * Affiche la ville cherchée par son ID
     * @param id de la ville
     * @return un Optional de la ville cherché par son id
     */

    @GetMapping("/{id}")
    public Ville getVilleById(@PathVariable int id) {
        return villeService.extractVille(id);
    }

    /**
     * Affiche la ville cherchée par son nom
     * @param nom
     * @return un Optional de la ville par son nom
     */

    @GetMapping("/{id}")
    public Ville getVilleByNom(@RequestParam String nom) {
        return villeService.extractVille(nom);
    }

    /**
     * Insère en base la ville entrée en paramètre
     * @param ville
     * @return la liste de toutes les villes après ajout de la nouvelle Ville
     */
    @PostMapping("/")
    public List<Ville> addVille(@RequestBody Ville ville) {
        return villeService.insertVille(ville);
    }


    @PutMapping("/{id}")
    public List<Ville> updateVille(@PathVariable int id, @RequestBody Ville ville) {
        return villeService.modifierVille(id, ville);
    }


    @DeleteMapping("/{id}")
    public List<Ville> deleteVille(@PathVariable int id) {

        return villeService.supprimerVille(id);
    }
}
