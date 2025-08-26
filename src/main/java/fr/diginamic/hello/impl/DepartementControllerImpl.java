package fr.diginamic.hello.impl;

import com.itextpdf.text.DocumentException;
import fr.diginamic.hello.controller.DepartementController;
import fr.diginamic.hello.dto.DepartementDTO;
import fr.diginamic.hello.dto.VilleDTO;
import fr.diginamic.hello.exceptions.ExceptionFonctionnelle;
import fr.diginamic.hello.services.IDepartementService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/departements")
public class DepartementControllerImpl implements DepartementController {

    private final IDepartementService DepartementService;

    public DepartementControllerImpl(DepartementServiceImpl departementServiceImpl) {
        this.DepartementService = departementServiceImpl;
    }


    /**
     * Récupère la liste de tous les départements.
     *
     * @return une liste de départements
     */

    @GetMapping
    @Override
    public List<DepartementDTO> getAllDepartements() {
        return DepartementService.getAllDepartements();
    }

    /**
     * Récupère un département par son code.
     *
     * @param code le code du département
     * @return le département correspondant au code
     */

    @GetMapping("/departement/{code}")
    @Override
    public DepartementDTO getDepartementByCode(@PathVariable String code) throws ExceptionFonctionnelle {
        return DepartementService.getDepartementByCode(code);
    }

    /**
     * Ajoute un nouveau département.
     *
     * @param departement le département à ajouter
     * @return le département ajouté
     */

    @PostMapping
    @Override
    public List<DepartementDTO> addDepartement(@RequestBody DepartementDTO departement) throws ExceptionFonctionnelle {
        return DepartementService.addDepartement(departement);
    }

    /**
     * Met à jour un département existant.
     *
     * @param code le code du département à mettre à jour
     * @param departement les nouvelles informations du département
     * @return le département mis à jour
     */

    @PutMapping("/departement/{code}")
    @Override
    public List<DepartementDTO> updateDepartement(@PathVariable String code, @RequestBody DepartementDTO departement) throws ExceptionFonctionnelle {
        return DepartementService.updateDepartement(code, departement);
    }

    /**
     * Supprime un département par son code.
     *
     * @param code le code du département à supprimer
     */

    @DeleteMapping("/departement/{code}")
    @Override
    public List<DepartementDTO> deleteDepartement(@PathVariable String code) throws ExceptionFonctionnelle {
        DepartementService.deleteDepartement(code);
        return DepartementService.getAllDepartements();
    }

    /**
     * Récupère le nombre de villes passé en paramètre dans un département donné
     * par exemple http://localhost:8080/departements/villes/34/5
     * @Param code du département
     * @Param nombre de villes à récupérer
     * @return une liste de villes dans le département spécifié
     */

    @GetMapping("/villes/{dep}/{nb}")
    @Override
    public List<VilleDTO> getVillesByDepartementAndNombre(@PathVariable("dep") String code,
                                                          @PathVariable("nb") int nombre) throws ExceptionFonctionnelle {

        return DepartementService.getVillesByDepartementAndNombre(code, nombre);
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
    @Override
    public List<VilleDTO> getVillesByDepartementAndPopulationRange(@RequestParam("code") String departementCode,
                                                                   @RequestParam("min") Integer populationMin,
                                                                   @RequestParam("max") Integer populationMax) throws ExceptionFonctionnelle {
        return DepartementService.getVillesByDepartementAndPopulationRange(departementCode, populationMin, populationMax);
    }

    @GetMapping("{code}/pdf")
    @Override
    public void ExportPdfDepartement(@PathVariable  String code, HttpServletResponse response) throws ExceptionFonctionnelle,
            IOException, DocumentException {
        DepartementService.exportPDFDepartement(code, response);
    }

}
