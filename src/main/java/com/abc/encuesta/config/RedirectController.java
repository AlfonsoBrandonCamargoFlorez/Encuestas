package com.abc.encuesta.config;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class RedirectController {

    @GetMapping("/redirect")
    public RedirectView redirectBasedOnRole(Authentication authentication) {
        // Determinar el rol y redirigir
        if (authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"))) {
            return new RedirectView("/admin");
        } else if (authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_USER"))) {
            return new RedirectView("/user");
        } else {
            return new RedirectView("/"); // Redirigir a una página predeterminada si el rol no coincide
        }
    }
}