package com.example.springprojet.controller;


import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String home(Authentication authentication) {
        if (authentication != null) {
            // Obtenez l'objet UserDetails de l'utilisateur authentifié
            org.springframework.security.core.userdetails.User userDetails = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
            String email = userDetails.getUsername(); // Obtenez l'email de l'utilisateur authentifié

            // Vérifiez l'email de l'utilisateur et redirigez en conséquence
            if ("admin@hotmail.com".equals(email)) {
                return "admin";
            } else if ("fournisseur@hotmail.com".equals(email)) {
                return "fournisseur";
            }
        }

        // Si l'utilisateur n'est pas connecté ou ne correspond à aucune condition, redirigez-le vers la page de connexion
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

}
