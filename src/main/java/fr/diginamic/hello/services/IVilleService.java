package fr.diginamic.hello.services;

import com.itextpdf.text.DocumentException;
import fr.diginamic.hello.dto.VilleDTO;
import fr.diginamic.hello.exceptions.ExceptionFonctionnelle;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.List;

public interface IVilleService {
    List<VilleDTO> extractVilles();

    Page<VilleDTO> getVillesPagination(int page, int size);

    VilleDTO extractVille(int idVille) throws ExceptionFonctionnelle;

    VilleDTO extractVille(String nom);

    List<VilleDTO> insertVille(VilleDTO villeDto) throws ExceptionFonctionnelle;

    List<VilleDTO> updateVille(int idVille, VilleDTO villeModifiee) throws ExceptionFonctionnelle;

    List<VilleDTO> deleteVille(int idVille) throws ExceptionFonctionnelle;

    List<VilleDTO> getVillesByNomContaining(String nom) throws ExceptionFonctionnelle;


    List<VilleDTO> getVillesByPopulationGreaterThan(int population) throws ExceptionFonctionnelle;

    List<VilleDTO> getVillesByPopulationBetweenAndOrderByPopulationDesc(int min, int max) throws ExceptionFonctionnelle;

    List<VilleDTO> getVillesByDepartementCodeAndPopulationGreaterThanOrderByPopulationDesc(String code,
                                                                                           int population) throws ExceptionFonctionnelle;

    List<VilleDTO> getVilleByDepartementCodeAndPopulationBetweenOrderByPopulationDesc(String code, Integer min
            , Integer max) throws ExceptionFonctionnelle;

    List<VilleDTO> getTopNVillesByPopulation() throws ExceptionFonctionnelle;

    void exportCsvVillesSupMin(Integer popMin, HttpServletResponse response) throws ExceptionFonctionnelle,
            IOException,
            DocumentException;




}
