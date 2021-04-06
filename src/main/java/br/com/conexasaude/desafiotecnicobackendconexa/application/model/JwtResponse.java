package br.com.conexasaude.desafiotecnicobackendconexa.application.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class JwtResponse {

    @JsonProperty("token")
    private final String token;

}

