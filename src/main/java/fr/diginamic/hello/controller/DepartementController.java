package fr.diginamic.hello.controller;

import com.itextpdf.text.DocumentException;
import fr.diginamic.hello.dto.DepartementDTO;
import fr.diginamic.hello.dto.VilleDTO;
import fr.diginamic.hello.exceptions.ExceptionFonctionnelle;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

public interface DepartementController {


    List<DepartementDTO> getAllDepartements();


    DepartementDTO getDepartementByCode(String code) throws ExceptionFonctionnelle;


    List<DepartementDTO> addDepartement(DepartementDTO departement) throws ExceptionFonctionnelle;


    List<DepartementDTO> updateDepartement(String code, @RequestBody DepartementDTO departement) throws ExceptionFonctionnelle;


    List<DepartementDTO> deleteDepartement(String code) throws ExceptionFonctionnelle;


    List<VilleDTO> getVillesByDepartementAndNombre(String code,
                                                   int nombre) throws ExceptionFonctionnelle;

    List<VilleDTO> getVillesByDepartementAndPopulationRange(String departementCode,
                                                            Integer populationMin,
                                                            Integer populationMax) throws ExceptionFonctionnelle;

    void ExportPdfDepartement(String code, HttpServletResponse response) throws ExceptionFonctionnelle, IOException,
            DocumentException;
}
