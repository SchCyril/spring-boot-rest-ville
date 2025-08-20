package fr.diginamic.hello.services;

import org.springframework.stereotype.Service;

@Service
public class HelloService {


    public String salutation() {
        return "Hello, je suis la classe service et je vous dis bonjour";
    }
}
