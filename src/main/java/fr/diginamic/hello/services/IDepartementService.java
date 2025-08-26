package fr.diginamic.hello.services;

import com.itextpdf.text.DocumentException;
import fr.diginamic.hello.dto.DepartementDTO;
import fr.diginamic.hello.dto.VilleDTO;
import fr.diginamic.hello.exceptions.ExceptionFonctionnelle;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public interface IDepartementService {

    List<DepartementDTO> getAllDepartements();

    DepartementDTO getDepartementByCode(String code) throws ExceptionFonctionnelle;

    List<DepartementDTO> addDepartement(DepartementDTO departementDto) throws ExceptionFonctionnelle;

    List<DepartementDTO> updateDepartement(String code, DepartementDTO departement) throws ExceptionFonctionnelle;

    List<DepartementDTO> deleteDepartement(String code) throws ExceptionFonctionnelle;

    List<VilleDTO> getVillesByDepartementAndNombre(String code, int nombre) throws ExceptionFonctionnelle;

    List<VilleDTO> getVillesByDepartementAndPopulationRange(String code, Integer populationMin,
                                                            Integer populationMax) throws ExceptionFonctionnelle;

    void exportPDFDepartement(String code, HttpServletResponse response) throws ExceptionFonctionnelle,
            DocumentException, IOException;

}
