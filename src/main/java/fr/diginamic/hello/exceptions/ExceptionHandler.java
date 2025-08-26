package fr.diginamic.hello.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler({ExceptionFonctionnelle.class})
    protected ResponseEntity<String> traiterErreurs(ExceptionFonctionnelle exceptionFonctionnelle) {
        return ResponseEntity.badRequest().body(exceptionFonctionnelle.getMessage());
    }
}
