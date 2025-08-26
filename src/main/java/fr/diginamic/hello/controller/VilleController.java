package fr.diginamic.hello.controller;

import com.itextpdf.text.DocumentException;
import fr.diginamic.hello.dto.DepartementDTO;
import fr.diginamic.hello.dto.VilleDTO;
import fr.diginamic.hello.exceptions.ExceptionFonctionnelle;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.List;

public interface VilleController {


    @Operation(summary = "Retourne la liste de tous les départements")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Liste des villes au format JSON",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema =
                    @Schema(implementation = DepartementDTO.class)))})
    })
    List<VilleDTO> getVilles();

    @Operation(summary = "Retourne la liste de tous les départements avec une pagination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Liste des villes au format JSON",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema =
                    @Schema(implementation = VilleDTO.class)))})
    })
    Page<VilleDTO> getVillesPagination(int page, int size) throws ExceptionFonctionnelle;

    VilleDTO getVilleById(int id) throws ExceptionFonctionnelle;

    VilleDTO getVilleByNom(String nom) throws ExceptionFonctionnelle;


    @Operation(summary = "Retourne la liste de tous les départements après création d'une nouvelle ville")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Créer une ville",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema =
                    @Schema(implementation = VilleDTO.class)))})
    })
    List<VilleDTO> addVille(VilleDTO villeDTO) throws ExceptionFonctionnelle;

    @Operation(summary = "Retourne la liste de tous les départements après modification d'une nouvelle ville")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Modifie une ville",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema =
                    @Schema(implementation = VilleDTO.class)))})
    })
    List<VilleDTO> updateVille(@Parameter(description = "Ajouter l'id obligatoire ", required = true) int id,
                               VilleDTO ville) throws ExceptionFonctionnelle;

    @Operation(summary = "Retourne la liste de tous les départements après suppression d'une nouvelle ville")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Supprime une ville",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = VilleDTO.class)),
                    }),
            @ApiResponse(responseCode = "400",
                    description = "Département non trouvé", content = @Content())
    })
    List<VilleDTO> deleteVille(@Parameter(description = "Identifiant de la ville à récupérer", example = "34", required = true) int id) throws ExceptionFonctionnelle;

    List<VilleDTO> getVillesByNomContaining(String nom) throws ExceptionFonctionnelle;

    List<VilleDTO> getVillesByPopulationGreaterThan(int population) throws ExceptionFonctionnelle;

    List<VilleDTO> getVillesByPopulationBetweenAndOrderByPopulationDesc(int populationMin,
                                                                        int populationMax) throws ExceptionFonctionnelle;

    List<VilleDTO> getVillesByDepartementCodeAndPopulationGreaterThanOderByPopulationDesc(String code,
                                                                                          int population) throws ExceptionFonctionnelle;

    List<VilleDTO> getVilleByDepartementCodeAndPopulationBetweenOderByPopulationDesc(String code,
                                                                                     int populationMin,
                                                                                     int populationMax) throws ExceptionFonctionnelle;

    List<VilleDTO> getTopNVillesByPopulation() throws ExceptionFonctionnelle;

    void exportCSV(Integer popMin, HttpServletResponse response) throws ExceptionFonctionnelle, IOException,
            DocumentException;
}
