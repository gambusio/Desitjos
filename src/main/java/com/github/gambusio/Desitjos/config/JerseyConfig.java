package com.github.gambusio.Desitjos.config;

import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@ApplicationPath("/")
@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        this.register(new CorsResponseFilter());
        this.packages("com.github.gambusio.Desitjos.controllers");
    }
}
