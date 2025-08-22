package fr.diginamic.hello.controller;

import fr.diginamic.hello.dto.VilleDTO;
import fr.diginamic.hello.entities.Ville;
import fr.diginamic.hello.services.VilleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/villes")
public class VilleController {

   private final VilleService villeService;

    public VilleController(VilleService villeService) {
        this.villeService = villeService;
    }

    /**
     * Affiche la liste de toutes les villes
     * @return une liste de villes
     */

    @GetMapping
    public List<VilleDTO> getVilles() {
        return villeService.extractVilles();
    }

    /**
     * Affiche la ville cherchée par son ID
     * @param id de la ville
     * @return un Optional de la ville cherché par son id
     */

    @GetMapping("/ville/{id}")
    public VilleDTO getVilleById(@PathVariable int id) {
        return villeService.extractVille(id);
    }

    /**
     * Affiche la ville cherchée par son nom
     * @param nom
     * @return un Optional de la ville par son nom
     */

    @GetMapping("/{nom}")
    public VilleDTO getVilleByNom(@PathVariable String nom) {
        return villeService.extractVille(nom);
    }

    /**
     * Insère en base la ville entrée en paramètre
     * @param ville
     * @return la liste de toutes les villes après ajout de la nouvelle Ville
     */
    @PostMapping("/ville")
    public List<VilleDTO> addVille(@RequestBody Ville ville) {
        return villeService.insertVille(VilleDTO.fromEntity(ville));
    }

    /**
     * Modifie la ville entrée en paramètre
     * @param id de la ville à modifier
     * @param ville
     * @return la liste de toutes les villes après modification de la Ville
     */

    @PutMapping("/ville/{id}")
    public List<VilleDTO> updateVille(@PathVariable int id, @RequestBody VilleDTO ville) {
        return villeService.updateVille(id, ville);
    }

    /**
     * Supprime la ville entrée en paramètre
     * @param id de la ville à supprimer
     * @return la liste de toutes les villes après suppression de la Ville
     */

    @DeleteMapping("/ville/{id}")
    public List<VilleDTO> deleteVille(@PathVariable int id) {
        return villeService.deleteVille(id);
    }
}
