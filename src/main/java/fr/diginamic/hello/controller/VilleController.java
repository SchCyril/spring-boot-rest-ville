package fr.diginamic.hello.controller;

import fr.diginamic.hello.dto.VilleDTO;
import fr.diginamic.hello.entities.Ville;
import fr.diginamic.hello.exceptions.ExceptionFonctionnelle;
import fr.diginamic.hello.services.VilleService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/villes")
public class VilleController implements InterfaceVilleController {

    private final VilleService villeService;

    public VilleController(VilleService villeService) {
        this.villeService = villeService;
    }

    /**
     * Affiche la liste de toutes les villes avec pagination
     * page : numéro de la page (0-indexed)
     * size : nombre d'éléments par page
     *
     * @return la liste de toutes les villes
     */



    @GetMapping()
    @Override
    public List<VilleDTO> getVilles() {
        return villeService.extractVilles();
    }

    /**
     * Récupère la liste de toutes les villes en Pagination
     * Exemple http://localhost:8080/villes/pagination?page=0&size=5
     *
     * @param page
     * @param size
     * @return la liste de toutes les villes
     */

    @GetMapping("/pagination")
    @Override
    public Page<VilleDTO> getVillesPagination(@RequestParam int page, @RequestParam int size) throws ExceptionFonctionnelle {
        return villeService.getVillesPagination(page, size);
    }

    /**
     * Affiche la ville cherchée par son ID
     *
     * @param id de la ville
     * @return un Optional de la ville cherché par son id
     */

    @GetMapping("/ville-id/{id}")
    @Override
    public VilleDTO getVilleById(@PathVariable int id) throws ExceptionFonctionnelle {
        return villeService.extractVille(id);
    }

    /**
     * Affiche la ville cherchée par son nom
     *
     * @param nom
     * @return un Optional de la ville par son nom
     */

    @GetMapping("/ville-nom/{nom}")
    @Override
    public VilleDTO getVilleByNom(@PathVariable String nom) throws ExceptionFonctionnelle {
        return villeService.extractVille(nom);
    }

    /**
     * Insère en base la ville entrée en paramètre
     *
     * @param villeDTO
     * @return la liste de toutes les villes après ajout de la nouvelle Ville
     */
    @PostMapping("/ville")
    @Override
    public List<VilleDTO> addVille(@RequestBody VilleDTO villeDTO) throws ExceptionFonctionnelle {
        return villeService.insertVille(villeDTO);
    }

    /**
     * Modifie la ville entrée en paramètre
     *
     * @param id    de la ville à modifier
     * @param ville
     * @return la liste de toutes les villes après modification de la Ville
     */

    @PutMapping("/ville/{id}")
    @Override
    public List<VilleDTO> updateVille(@PathVariable int id, @RequestBody VilleDTO ville) throws ExceptionFonctionnelle {
        return villeService.updateVille(id, ville);
    }

    /**
     * Supprime la ville entrée en paramètre
     *
     * @param id de la ville à supprimer
     * @return la liste de toutes les villes après suppression de la Ville
     */

    @DeleteMapping("/ville/{id}")
    @Override
    public List<VilleDTO> deleteVille(@PathVariable int id) throws ExceptionFonctionnelle {
        return villeService.deleteVille(id);
    }

    /**
     * Récupère une liste de villes dont le nom contient la chaîne spécifiée.
     * Par exemple : /villes/contains?nom=par
     *
     * @param nom la chaîne à rechercher dans les noms de villes
     * @return une liste de villes dont le nom contient la chaîne spécifiée
     */
    @GetMapping("/contains")
    @Override
    public List<VilleDTO> getVillesByNomContaining(@RequestParam String nom) throws ExceptionFonctionnelle {
        return villeService.getVillesByNomContaining(nom);
    }

    /**
     * Récupère une liste de villes avec une population supérieure à la valeur spécifiée
     * Par exemple : /villes/popMin/10000
     *
     * @param population la population minimale
     * @return une liste de villes avec une population supérieure à la valeur spécifiée
     */
    @GetMapping("/popMin/{population}")
    @Override
    public List<VilleDTO> getVillesByPopulationGreaterThan(@PathVariable int population) throws ExceptionFonctionnelle {
        return villeService.getVillesByPopulationGreaterThan(population);
    }

    /**
     * Récupère une liste de villes avec une population comprise entre les valeurs spécifiées
     * Triée par population décroissante.
     * Par exemple : /villes/populationBetween?populationMin=10000&populationMax=50000
     *
     * @param populationMin la population minimale
     * @param populationMax la population maximale
     * @return une liste de villes avec une population comprise entre les valeurs spécifiées
     */
    @GetMapping("/populationBetween")
    @Override
    public List<VilleDTO> getVillesByPopulationBetweenAndOrderByPopulationDesc(@RequestParam int populationMin,
                                                                               @RequestParam int populationMax) throws ExceptionFonctionnelle {
        return villeService.getVillesByPopulationBetweenAndOrderByPopulationDesc(populationMin, populationMax);
    }

    /**
     * Récupère une liste de villes d'un département avec une population supérieure à la valeur spécifiée
     * Triée par population décroissante.
     * Par exemple : /villes/departement/27/popMin/100000
     *
     * @param code       le code du département
     * @param population la population minimale
     * @return une liste de villes d'un département avec une population supérieure à la valeur spécifiée
     */
    @GetMapping("/departement/{code}/popMin/{population}")
    @Override
    public List<VilleDTO> getVillesByDepartementCodeAndPopulationGreaterThanOderByPopulationDesc(@PathVariable String code,
                                                                                                 @PathVariable int population) throws ExceptionFonctionnelle {
        return villeService.getVillesByDepartementCodeAndPopulationGreaterThanOrderByPopulationDesc(code, population);
    }

    /**
     * Récupère une liste de villes d'un département avec une population comprise entre les valeurs spécifiées
     * Triée par population décroissante.
     * Par exemple : /villes/departement/34/populationBetween?populationMin=10000&populationMax=50000
     *
     * @param code          le code du département
     * @param populationMin la population minimale
     * @param populationMax la population maximale
     * @return une liste de villes d'un département avec une population comprise entre les valeurs spécifiées
     */

    @GetMapping("/departement/{code}/populationBetween")
    @Override
    public List<VilleDTO> getVilleByDepartementCodeAndPopulationBetweenOderByPopulationDesc(@PathVariable String code,
                                                                                            @RequestParam int populationMin,
                                                                                            @RequestParam int populationMax)  throws ExceptionFonctionnelle{
        return villeService.getVilleByDepartementCodeAndPopulationBetweenOrderByPopulationDesc(code, populationMin,
                populationMax);
    }

    /**
     * @return la liste des N villes les plus peuplées
     * @throws ExceptionFonctionnelle
     */

    @GetMapping("/top")
    @Override
    public List<VilleDTO> getTopNVillesByPopulation() throws ExceptionFonctionnelle {
        return villeService.getTopNVillesByPopulation();
    }
}
