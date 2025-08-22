package fr.diginamic.hello.controller;

import fr.diginamic.hello.dto.DepartementDTO;
import fr.diginamic.hello.dto.VilleDTO;
import fr.diginamic.hello.services.DepartementService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departements")
public class DepartementController {

    private final DepartementService departementService;

    public DepartementController(DepartementService departementService) {
        this.departementService = departementService;
    }


    /**
     * Récupère la liste de tous les départements.
     *
     * @return une liste de départements
     */

    @GetMapping
    public List<DepartementDTO> getAllDepartements() {
        return departementService.getAllDepartements();
    }

    /**
     * Récupère un département par son code.
     *
     * @param code le code du département
     * @return le département correspondant au code
     */

    @GetMapping("/departement/{code}")
    public DepartementDTO getDepartementByCode(@PathVariable String code) {
        return departementService.getDepartementByCode(code);
    }

    /**
     * Ajoute un nouveau département.
     *
     * @param departement le département à ajouter
     * @return le département ajouté
     */

    @PostMapping
    public List<DepartementDTO> addDepartement(@RequestBody DepartementDTO departement) {
        return departementService.addDepartement(departement);
    }

    /**
     * Met à jour un département existant.
     *
     * @param code le code du département à mettre à jour
     * @param departement les nouvelles informations du département
     * @return le département mis à jour
     */

    @PutMapping("/departement/{code}")
    public List<DepartementDTO> updateDepartement(@PathVariable String code, @RequestBody DepartementDTO departement) {
        return departementService.updateDepartement(code, departement);
    }

    /**
     * Supprime un département par son code.
     *
     * @param code le code du département à supprimer
     */

    @DeleteMapping("/departement/{code}")
    public List<DepartementDTO> deleteDepartement(@PathVariable String code) {
        departementService.deleteDepartement(code);
        return departementService.getAllDepartements();
    }

    /**
     * Récupère le nombre de villes passé en paramètre dans un département donné
     * par exemple http://localhost:8080/departements/villes/34/5
     * @Param code du département
     * @Param nombre de villes à récupérer
     * @return une liste de villes dans le département spécifié
     */

    @GetMapping("/villes/{dep}/{nb}")
    public List<VilleDTO> getVillesByDepartementAndNombre(@PathVariable("dep") String code,
                                                          @PathVariable("nb") int nombre) {

        return departementService.getVillesByDepartementAndNombre(code, nombre);
    }


    /**
     * Récupère les villes d'un département avec une plage de population.
     * Par exemple http://localhost:8080/departements/villes/search?code=34&min=25000&max=50000
     *
     * @param departementCode le code du département
     * @param populationMin   la population minimale
     * @param populationMax   la population maximale
     * @return une liste de villes dans le département spécifié avec la plage de population
     */
    @GetMapping("/villes/search")
    public List<VilleDTO> getVillesByDepartementAndPopulationRange(@RequestParam("code") String departementCode,
                                                                @RequestParam("min") Integer populationMin,
                                                                @RequestParam("max") Integer populationMax) {
        return departementService.getVillesByDepartementAndPopulationRange(departementCode, populationMin, populationMax);
    }

}
